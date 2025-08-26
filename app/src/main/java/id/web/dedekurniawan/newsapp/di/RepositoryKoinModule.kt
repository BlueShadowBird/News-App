package id.web.dedekurniawan.newsapp.di

import androidx.room.Room
import id.web.dedekurniawan.newsapp.data.local.database.room.NewsDatabase
import id.web.dedekurniawan.newsapp.data.repository.NewsApiRepository
import id.web.dedekurniawan.newsapp.domain.repository.NewsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryKoinModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "MovieXplorer.db"
        ).fallbackToDestructiveMigration()
            .build()
            .getNewsDao()
    }

    single<NewsRepository>{
        NewsApiRepository(get(), get())
    }
}