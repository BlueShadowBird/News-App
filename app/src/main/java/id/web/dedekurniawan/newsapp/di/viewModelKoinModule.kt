package id.web.dedekurniawan.newsapp.di

import id.web.dedekurniawan.newsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelKoinModule = module{
    viewModel {
        NewsViewModel(get())
    }
}