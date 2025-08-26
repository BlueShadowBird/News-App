package id.web.dedekurniawan.newsapp.data.remote

import id.web.dedekurniawan.newsapp.data.remote.response.NewsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/top-headlines?country=us")
    suspend fun retrieveNews(@Query("q")query: String?): NewsListResponse
}