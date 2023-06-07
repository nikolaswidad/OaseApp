package com.nikolaswidad.oaseapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.nikolaswidad.oaseapp.BuildConfig
import com.nikolaswidad.oaseapp.data.local.entity.NewsEntity
import com.nikolaswidad.oaseapp.data.local.room.NewsDao
import com.nikolaswidad.oaseapp.data.remote.retrofit.ApiService
import com.nikolaswidad.oaseapp.model.NewsBookmark

class NewsRepository(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {
    private val newsBookmarks = mutableListOf<NewsBookmark>()
//
//    init {
//        if (newsBookmarks.isEmpty()){
//            FakeNewsDataSource.dummyNews.forEach {
//                newsBookmarks.add(NewsBookmark(it, false))
//            }
//        }
//    }
//
//    fun getAllNews(): Flow<List<NewsBookmark>> {
//        return flowOf(newsBookmarks)
////        return flowOf<List<BookmarkNews>>(bookmarkNews)
//
//    }
//
//    fun getBookmarkNewsById(newsId: Long): NewsBookmark {
//        return newsBookmarks.first{
//            it.news.id == newsId
//        }
//    }
//
//    fun updateBookmarkNews(newsId: Long, newIsBookmarkValue: Boolean ):Flow<Boolean> {
//        val index = newsBookmarks.indexOfFirst { it.news.id == newsId }
//        val result = if (index >= 0) {
//            val newsBookmark = newsBookmarks[index]
//            newsBookmarks[index] =
//                newsBookmark.copy(news = newsBookmark.news, isBookmarked = newIsBookmarkValue)
//            true
//        } else {
//            false
//        }
//        return flowOf(result)
//    }
//
//    fun getAddedBookmarkNews(): Flow<List<NewsBookmark>> {
//        return getAllNews()
//            .map { newsBookmarks ->
//                newsBookmarks.filter { newsBookmark ->
//                    newsBookmark.isBookmarked
//                }
//            }
//    }

    // From Source JEtpack
    fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNews(BuildConfig.API_KEY)
            val articles = response.articles
            val newsList = articles.map { article ->
                NewsEntity(
                    article.title,
                    article.publishedAt,
                    article.urlToImage,
                    article.url
                )
            }
            emit(Result.Success(newsList))
        } catch (e: Exception) {
            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }

    suspend fun saveNews(news: NewsEntity) {
        newsDao.saveNews(news)
    }

    suspend fun deleteNews(title: String) {
        newsDao.deleteNews(title)
    }

    fun isNewsBookmarked(title: String): LiveData<Boolean> {
        return newsDao.isNewsBookmarked(title)
    }

//    fun getHeadlineNews(): Flow<Result<List<NewsEntity>>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = apiService.getNews(BuildConfig.API_KEY)
//            val articles = response.articles
//            val newsList = articles.map { article ->
//                NewsEntity(
//                    article.title,
//                    article.publishedAt,
//                    article.urlToImage,
//                    article.url
//                )
//            }
//            emit(Result.Success(newsList))
//        } catch (e: Exception) {
//            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
//            emit(Result.Error(e.message.toString()))
//        }
//    }
//
//    fun getBookmarkedNews(): Flow<List<NewsEntity>> {
//        return newsDao.getBookmarkedNews()
//            .asFlow()
//    }
//
//    suspend fun saveNews(news: NewsEntity) {
//        newsDao.saveNews(news)
//    }
//
//    suspend fun deleteNews(title: String) {
//        newsDao.deleteNews(title)
//    }
//
//    fun isNewsBookmarked(title: String): Flow<Boolean> {
//        return newsDao.isNewsBookmarked(title)
//            .asFlow()
//    }


    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newsDao)
            }.also { instance = it }
    }
}
