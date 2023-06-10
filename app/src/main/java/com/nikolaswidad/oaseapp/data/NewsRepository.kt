package com.nikolaswidad.oaseapp.data

import com.nikolaswidad.oaseapp.data.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepository {

    private val news = mutableListOf<NewsEntity>()


    fun getAllNews(): Flow<List<NewsEntity>>{
        return flowOf(news)
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(): NewsRepository =
            instance ?: synchronized(this) {
                NewsRepository().apply {
                    instance = this
                }
            }
    }
}
