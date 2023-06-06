package com.nikolaswidad.oaseapp.di

import com.nikolaswidad.oaseapp.data.NewsRepository

object Injection {
    fun provideRepository(): NewsRepository {
        return NewsRepository.getInstance()
    }
}