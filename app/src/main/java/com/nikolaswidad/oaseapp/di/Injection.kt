package com.nikolaswidad.oaseapp.di

import android.content.Context
import com.nikolaswidad.oaseapp.data.NewsRepository
import com.nikolaswidad.oaseapp.data.local.room.NewsDatabase
import com.nikolaswidad.oaseapp.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}