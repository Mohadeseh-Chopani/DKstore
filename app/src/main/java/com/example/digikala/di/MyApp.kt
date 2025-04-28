package com.example.digikala.di

import android.app.Application
import com.example.digikala.data.dataSource.HomePageDataSourceImp
import com.example.digikala.data.dataSource.ProductDataSourceImp
import com.example.digikala.data.repository.HomeRepositoryImp
import com.example.digikala.data.repository.ProductRepositoryImp
import com.example.digikala.network.StoreApiProvider
import com.example.digikala.view.HomeViewModel
import com.example.digikala.view.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            singleOf(StoreApiProvider::getApiService)

            //home page
            factoryOf(::HomePageDataSourceImp)
            factoryOf(::HomeRepositoryImp)
            viewModelOf(::HomeViewModel)

            //product page
            factoryOf(::ProductDataSourceImp)
            factoryOf(::ProductRepositoryImp)
            viewModelOf(::ProductViewModel)
        }

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}