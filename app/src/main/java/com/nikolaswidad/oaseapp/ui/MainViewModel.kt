package com.nikolaswidad.oaseapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolaswidad.oaseapp.R
import com.nikolaswidad.oaseapp.domain.model.Article
import com.nikolaswidad.oaseapp.domain.repository.ArticlesRepository
import com.nikolaswidad.oaseapp.domain.repository.ArticlesRepositoryStatus
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ArticlesRepository) : ViewModel() {

    constructor (repository: ArticlesRepository, useFakeData: Boolean) : this(repository) {
//        if(useFakeData) makeFakeArticles()
    }

    var allArticles: List<Article>? by mutableStateOf(null)
        private set
    var bookmarkedArticles: List<Article>? by mutableStateOf(null)
        private set
    var unreadArticles: List<Article>? by mutableStateOf(null)
        private set
    var currentArticle: Article? by mutableStateOf(null)
        private set

    var showSnackBarStringId: Int? by mutableStateOf(null)
        private set

    var searchQuery: String by mutableStateOf("")
        private set
    var searchedArticles: List<Article>? by mutableStateOf(null)
        private set
    var searchedResultResId: Int? by mutableStateOf(null)
        private set

    var isRefreshing: Boolean by mutableStateOf(false)
        private set

    init {
        collectFlows()
    }

    fun refresh() {
        viewModelScope.launch {
            repository.refresh()
        }
    }

    fun clearShowSnackBarStringId() {
        showSnackBarStringId = null
    }

    fun clearSearchQuery() {
        searchQuery = ""
        searchedArticles = null
        searchedResultResId = null
    }

    fun onNavigateToArticleScreen(id: Int) {
        currentArticle = getArticle(id)
    }


    fun onBookmarkClick(id: Int) = viewModelScope.launch {
        val article = getArticle(id)
        repository.updateArticle(article.copy(bookmarked = !article.bookmarked))

        updateSearchedArticles()
    }

    fun onSearchQueryChanged(value: String) {
        searchQuery = value
    }

//    fun onAllArticlesSearch() = viewModelScope.launch {
//        searchedArticles = allArticles!!.filter { article ->
//            article.title.contains(searchQuery, ignoreCase = true)
//        }
//
//        searchedResultResId = R.string.all_articles
//    }


    fun onBookmarkedArticlesSearch() {
        searchedArticles = bookmarkedArticles!!.filter { article ->
            article.title.contains(searchQuery, ignoreCase = true)
        }

        searchedResultResId = R.string.bookmarked_articles
    }


    private fun getArticle(id: Int): Article {
        val article = allArticles!!.find { article ->
            article.id == id
        }

        return article!!
    }

    private suspend fun updateSearchedArticles() {
        if (searchQuery.isEmpty()) return

        if (searchedResultResId == R.string.all_articles) {
            searchedArticles = repository.getAllArticlesByTitle(searchQuery)

        } else if (searchedResultResId == R.string.bookmarked_articles) {
            searchedArticles = repository.getBookmarkedArticlesByTitle(searchQuery)

        } else {
            throw Exception("Unexpected updateSearchedArticles()!")
        }
    }


//    private fun makeFakeArticles() {
//        var articles: MutableList<Article> = mutableListOf()
//        repeat(10) {
//            articles.add(Utils.createArticle())
//        }
//        allArticles = articles
//
//        articles = mutableListOf()
//        repeat(10) {
//            articles.add(Utils.createArticle(bookmarked = true, read = true))
//        }
//        bookmarkedArticles = articles
//
//        unreadArticles = articles
//
//        currentArticle = articles[0]
//    }

     fun collectFlows() {
        viewModelScope.launch {

            launch {
                repository.statusFlow.collect { status ->
                    isRefreshing = status is ArticlesRepositoryStatus.IsLoading

                    if(status is ArticlesRepositoryStatus.Fail) {
                        showSnackBarStringId = R.string.no_internet
                    }

                }
            }

            launch {
                repository.articlesFlow.collect { articles ->
                    allArticles = articles
                }
            }

            launch {
                repository.bookmarkedArticlesFlow.collect { articles ->
                    bookmarkedArticles = articles
                }
            }

            launch {
                repository.unreadArticlesFlow.collect { articles ->
                    unreadArticles = articles
                }
            }
        }
    }
}