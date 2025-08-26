package id.web.dedekurniawan.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("publishedAt")
    val publishedAt: String,

    @field:SerializedName("author")
    val author: String?,

    @field:SerializedName("urlToImage")
    val urlToImage: String?,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("source")
    val source: Source,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("content")
    val content: String
)

data class Source(


    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String
)