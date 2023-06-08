package com.nikolaswidad.oaseapp.domain.repository

import com.nikolaswidad.oaseapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {
    val statusFlow: Flow<ArticlesRepositoryStatus>

    val articlesFlow: Flow<List<Article>>

    val unreadArticlesFlow: Flow<List<Article>>

    val bookmarkedArticlesFlow: Flow<List<Article>>

    suspend fun refresh(): ArticlesRepositoryStatus

    suspend fun updateArticle(article: Article)

    suspend fun getArticle(id: Int) : Article

    suspend fun getAllArticlesByTitle(title: String): List<Article>

    suspend fun getUnreadArticlesByTitle(title: String): List<Article>

    suspend fun getBookmarkedArticlesByTitle(title: String): List<Article>
}
