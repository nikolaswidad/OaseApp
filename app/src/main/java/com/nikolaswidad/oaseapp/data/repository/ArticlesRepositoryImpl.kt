package com.nikolaswidad.oaseapp.data.repository

import android.content.Context
import com.nikolaswidad.oaseapp.domain.model.Article
import com.nikolaswidad.oaseapp.domain.repository.ArticlesRepository
import com.nikolaswidad.oaseapp.domain.repository.ArticlesRepositoryStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesRepositoryImpl private constructor(
    private val database: ArticlesDatabase,
    private val webService: WebService,
) : ArticlesRepository {

    companion object {
        @Volatile
        private lateinit var instance: ArticlesRepository

        fun getInstance(context: Context): ArticlesRepository {
            synchronized(this) {
                if(!::instance.isInitialized) {
                    instance = ArticlesRepositoryImpl(
                        ArticlesDatabase.getInstance(context),
                        WebService(),
                    )
                }
                return instance
            }
        }
    }

    private val urls = listOf(
        "https://vtsen.hashnode.dev/rss.xml",
    )

    private var newArticlesFound: Boolean = false
    private var _status: ArticlesRepositoryStatus = ArticlesRepositoryStatus.Invalid
    override val statusFlow: Flow<ArticlesRepositoryStatus> = flow {
        while(true) {
            delay(500)
            emit(_status)
        }
    }

    override val articlesFlow = database.selectAllArticles().map { articlesEntity ->
        articlesEntity.asArticles()
    }

    override val unreadArticlesFlow = database.selectUnreadArticles().map { articleEntity ->
        articleEntity.asArticles()
    }

    override val bookmarkedArticlesFlow = database.selectBookmarkedArticles().map { articleEntity ->
        articleEntity.asArticles()
    }

    override suspend fun refresh(): ArticlesRepositoryStatus = withContext(Dispatchers.IO) {

        newArticlesFound = false
        _status = ArticlesRepositoryStatus.IsLoading

        try {
            val articlesFeed = fetchArticlesFeed()
            updateDatabase(articlesFeed.asArticleEntities())
            _status = ArticlesRepositoryStatus.Success(newArticlesFound)

        } catch(e: Exception) {
            e.printStackTrace()
            _status = ArticlesRepositoryStatus.Fail
        }

        return@withContext _status
    }

    override suspend fun updateArticle(article: Article) = withContext(Dispatchers.IO) {
        database.updateArticle(article.asArticleEntity())
    }

    override suspend fun getArticle(id: Int) = withContext(Dispatchers.IO) {
        database.selectArticleById(id).asArticle()
    }

    override suspend fun getAllArticlesByTitle(title: String): List<Article> = withContext(
        Dispatchers.IO) {
        return@withContext database.selectAllArticlesByTitle(title).asArticles()
    }

    override suspend fun getUnreadArticlesByTitle(title: String): List<Article> = withContext(
        Dispatchers.IO) {
        return@withContext database.selectUnreadArticlesByTitle(title).asArticles()
    }

    override suspend fun getBookmarkedArticlesByTitle(title: String): List<Article> = withContext(
        Dispatchers.IO) {
        return@withContext database.selectBookmarkedArticlesByTitle(title).asArticles()
    }

    private suspend fun fetchArticlesFeed() : List<ArticleFeed> = coroutineScope {
        val results = mutableListOf<ArticleFeed>()
        val jobs = mutableListOf<Job>()

        for(url in urls) {
            val job = launch {
                val xmlString = webService.getXMlString(url)
                val articleFeeds = FeedParser().parse(xmlString)
                results.addAll(articleFeeds)
            }

            jobs.add(job)
        }

        jobs.joinAll()

        return@coroutineScope results
    }

    private suspend fun updateDatabase(articleEntities: List<ArticleEntity>) = coroutineScope  {
        for(articleEntity in articleEntities) {
            launch{
                val articleFound = database.selectArticleByLink(articleEntity.link)
                if(articleFound == null) {
                    database.insertArticle(articleEntity)
                    newArticlesFound = true
                } else {
                    //Important Note:
                    // (1) articleEntity.id is different than the one in articleFound.id (database)
                    // (2) We want to keep the saved bookmarked and read articles, do not want to overwrites it
                    val data = articleEntity.copy(
                        id = articleFound.id,
                        bookmarked = articleFound.bookmarked,
                        read = articleFound.read,
                    )
                    database.updateArticle(data)
                }
            }
        }
    }
}