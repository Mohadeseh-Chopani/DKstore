package com.example.digikala.di

import android.app.Application
import androidx.room.Room
import com.example.digikala.data.dataSource.local.AppDatabase
import com.example.digikala.data.dataSource.local.Dao
import com.example.digikala.data.dataSource.local.ShoppingCard.ShoppingCardDataSourceImp
import com.example.digikala.data.dataSource.remote.CategoriesDataSourceImp
import com.example.digikala.data.dataSource.remote.HomePageDataSourceImp
import com.example.digikala.data.dataSource.remote.ProductDataSourceImp
import com.example.digikala.data.dataSource.remote.SearchDataSourceImp
import com.example.digikala.data.repository.CategoriesRepositoryImp
import com.example.digikala.data.repository.HomeRepositoryImp
import com.example.digikala.data.repository.ProductRepositoryImp
import com.example.digikala.data.repository.SearchRepositoryImp
import com.example.digikala.data.repository.ShoppingCardRepositoryImp
import com.example.digikala.network.StoreApiProvider
import com.example.digikala.view.CategoriesViewModel
import com.example.digikala.view.HomeViewModel
import com.example.digikala.view.ProductViewModel
import com.example.digikala.view.SearchViewModel
import com.example.digikala.view.ShoppingCardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
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

            singleOf(AppDatabase::getDatabase)
            singleOf(AppDatabase::appDao)


            //home page
            factoryOf(::HomePageDataSourceImp)
            factoryOf(::HomeRepositoryImp)
            viewModelOf(::HomeViewModel)

            //product page
            factoryOf(::ProductDataSourceImp)
            factoryOf(::ProductRepositoryImp)
            viewModelOf(::ProductViewModel)

            //categories page
            factoryOf(::CategoriesDataSourceImp)
            factoryOf(::CategoriesRepositoryImp)
            viewModelOf(::CategoriesViewModel)

            //search page
            factoryOf(::SearchDataSourceImp)
            factoryOf(::SearchRepositoryImp)
            viewModelOf(::SearchViewModel)

            //shopping card page
            factoryOf(::ShoppingCardDataSourceImp)
            factoryOf(::ShoppingCardRepositoryImp)
            viewModelOf(::ShoppingCardViewModel)

        }

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}