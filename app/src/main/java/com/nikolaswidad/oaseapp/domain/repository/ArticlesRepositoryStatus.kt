package com.nikolaswidad.oaseapp.domain.repository

sealed interface ArticlesRepositoryStatus {
    object Invalid: ArticlesRepositoryStatus

    object IsLoading: ArticlesRepositoryStatus

    data class Success(val withNewNews: Boolean): ArticlesRepositoryStatus

    object Fail: ArticlesRepositoryStatus
}