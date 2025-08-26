package id.web.dedekurniawan.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("code")
	val errorCode: String? = null,

	@field:SerializedName("message")
	val errorMessage: String? = null,

	@field:SerializedName("articles")
	val news: List<NewsResponse>,

	@field:SerializedName("totalResults")
	val totalResults: Int
)
