package id.web.dedekurniawan.newsapp.di

import id.web.dedekurniawan.newsapp.data.remote.ApiService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Properties
import java.util.concurrent.TimeUnit

val networkKoinModule = module {
    single {
        val properties = Properties()
        properties.load(androidContext().assets.open("api.properties"))

        val url = properties.getProperty("url")!!
        val apiKey = properties.getProperty("key")!!

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url

                // Tambahkan apiKey ke query parameter
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("apiKey", apiKey)
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)

        retrofitBuilder.client(okHttpClient)
            .build().create(ApiService::class.java)
    }
}