package id.web.dedekurniawan.newsapp

import android.app.Application
import androidx.collection.objectListOf
import id.web.dedekurniawan.newsapp.di.networkKoinModule
import id.web.dedekurniawan.newsapp.di.repositoryKoinModule
import id.web.dedekurniawan.newsapp.di.useCaseKoinModule
import id.web.dedekurniawan.newsapp.di.viewModelKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApplication)
            modules(
                listOf(
//                    frameworkKoinModule,
                    repositoryKoinModule,
                    useCaseKoinModule,
                    networkKoinModule,
                    viewModelKoinModule
                )
            )
        }
    }
}