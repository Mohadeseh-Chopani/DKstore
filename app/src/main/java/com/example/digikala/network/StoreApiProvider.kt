package com.example.digikala.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class StoreApiProvider {

    class ArrayOrObjectTypeAdapterFactory : TypeAdapterFactory {
        override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
            val delegateAdapter = gson.getDelegateAdapter(this, type)

            return object : TypeAdapter<T>() {
                override fun write(out: JsonWriter?, value: T?) {
                    delegateAdapter.write(out, value)
                }

                override fun read(reader: JsonReader?): T? {
                    // بررسی نوع داده (آرایه یا شیء)
                    if (reader == null) return null

                    when (reader.peek()) {

                        JsonToken.BEGIN_ARRAY -> {
                            reader.beginArray()
                            if (!reader.hasNext()) {
                                reader.endArray()
                                return null // آرایه خالی را به نال تبدیل می‌کنیم
                            }
                            return delegateAdapter.read(reader)
                        }

                        JsonToken.BEGIN_OBJECT -> {
                            reader.beginObject()
                            return delegateAdapter.read(reader) // شیء را دسیریالایز می‌کنیم
                        }

                        JsonToken.NAME -> {
                            reader.nextName()
                        }
                        else -> return delegateAdapter.read(reader) // اگر چیز دیگری بود، از دیفالت استفاده می‌کنیم
                    }
                    return TODO("Provide the return value")
                }
            }
        }
    }


    companion object {
        val BASE_URL = "https://api.one-api.ir/digikala/v1/"

        val API_KEY: String = "643704:66e9936b03835"

        // Create a lenient Gson instance
        val gson = GsonBuilder()
//            .registerTypeAdapterFactory(ArrayOrObjectTypeAdapterFactory())
            .setLenient()
            .create()

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("one-api-token", API_KEY)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


        fun getApiService(): StoreApiService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

            val storeApiService: StoreApiService = retrofit.create(StoreApiService::class.java)

            return storeApiService
        }
    }

}