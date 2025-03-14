package com.example.digikala.di

import android.app.Application
import com.example.digikala.data.dataSource.HomeDataSourceImp
import com.example.digikala.data.repository.HomeRepositoryImp
import com.example.digikala.network.StoreApiProvider
import com.example.digikala.view.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            singleOf(StoreApiProvider::getApiService)

            //home page
            factoryOf(::HomeDataSourceImp)
            factoryOf(::HomeRepositoryImp)
            viewModelOf(::HomeViewModel)
        }
    }
}