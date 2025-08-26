package id.web.dedekurniawan.newsapp.core

sealed class Result<R>(val data: R? = null, val message: String? = null){
    class Loading<R>(data: R? = null): Result<R>(data)
    class Success<R>(data: R): Result<R>(data)
    class Error<R>(message: String, data: R?=null): Result<R>(data, message)
}