package com.nikolaswidad.oaseapp.di

import com.nikolaswidad.oaseapp.domain.repository.ArticlesRepository

object Injection {
    fun provideRepository(): ArticlesRepository {
        return ArticlesRepository.getInstance()
    }
}