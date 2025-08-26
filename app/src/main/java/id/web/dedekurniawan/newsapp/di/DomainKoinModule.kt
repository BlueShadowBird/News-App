package id.web.dedekurniawan.newsapp.di

import id.web.dedekurniawan.newsapp.domain.usecase.NewsInteractor
import id.web.dedekurniawan.newsapp.domain.usecase.NewsUseCase
import org.koin.dsl.module

val useCaseKoinModule = module {
    single<NewsUseCase> {
        NewsInteractor(
            get()
        )
    }
}