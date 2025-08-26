package id.web.dedekurniawan.newsapp.data.mapper

import id.web.dedekurniawan.newsapp.data.local.database.entity.NewsEntity
import id.web.dedekurniawan.newsapp.data.remote.response.NewsResponse
import id.web.dedekurniawan.newsapp.domain.model.News

fun NewsResponse.toDomainModel() = News(
    title = title,
    description = description,
    author = author,
    sourceName = source.name,
    url = url,
    imageUrl = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun NewsEntity.toDomainModel() = News(
    title = title,
    description = description,
    author = author,
    sourceName = sourceName,
    url = url,
    imageUrl = imageUrl,
    publishedAt = publishedAt,
    content = content
)

fun News.toEntityClass() = NewsEntity(
    title = title,
    description = description,
    author = author,
    sourceName = sourceName,
    url = url,
    imageUrl = imageUrl,
    publishedAt = publishedAt,
    content = content
)