package com.nikolaswidad.oaseapp.data

import com.nikolaswidad.oaseapp.model.NewsBookmark
import com.nikolaswidad.oaseapp.model.FakeNewsDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository {

    private val newsBookmarks = mutableListOf<NewsBookmark>()

    init {
        if (newsBookmarks.isEmpty()){
            FakeNewsDataSource.dummyNews.forEach {
                newsBookmarks.add(NewsBookmark(it, false))
            }
        }
    }

    fun getAllNews(): Flow<List<NewsBookmark>> {
        return flowOf(newsBookmarks)
//        return flowOf<List<BookmarkNews>>(bookmarkNews)

    }

    fun getBookmarkNewsById(newsId: Long): NewsBookmark {
        return newsBookmarks.first{
            it.news.id == newsId
        }
    }

    fun updateBookmarkNews(newsId: Long, newIsBookmarkValue: Boolean ):Flow<Boolean> {
        val index = newsBookmarks.indexOfFirst { it.news.id == newsId }
        val result = if (index >= 0) {
            val newsBookmark = newsBookmarks[index]
            newsBookmarks[index] =
                newsBookmark.copy(news = newsBookmark.news, isBookmarked = newIsBookmarkValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedBookmarkNews(): Flow<List<NewsBookmark>> {
        return getAllNews()
            .map { newsBookmarks ->
                newsBookmarks.filter { newsBookmark ->
                    newsBookmark.isBookmarked
                }
            }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository?= null

        fun getInstance(): NewsRepository =
            instance ?: synchronized(this) {
                NewsRepository().apply {
                    instance = this
                }
            }
    }
}
