package com.nikolaswidad.oaseapp.ui.common

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.nikolaswidad.oaseapp.ui.MainViewModel

@Composable
fun ArticlesScreen(
    viewModel: MainViewModel,
    articles: List<Article>,
    navigateToArticle: (Int) -> Unit,
    noArticlesDescStrResId: Int,
) {
    if (articles.isEmpty()) {
        NoArticlesScreen(noArticlesDescStrResId)
        return
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(viewModel.isRefreshing),
        onRefresh = { viewModel.refresh() }
    ) {
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            items(items = articles) { article ->
                ArticleCard(
                    article = article,
                    onArticleCardClick = navigateToArticle,
                    onBookmarkClick = viewModel::onBookmarkClick,
                    onShareClick = {
                        shareArticle(context, article.link)
                    },
                    onReadClick = viewModel::onReadClick
                )
            }
        }
    }
}

@Composable
private fun NoArticlesScreen(noArticlesDescStrResId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_articles),
            style = MaterialTheme.typography.h4,
        )

        Spacer(Modifier.padding(PaddingSmall))
        Text(
            text = stringResource(noArticlesDescStrResId),
        )
    }
}

private fun shareArticle(context: Context, link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    ContextCompat.startActivity(context, shareIntent, null)
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = MainViewModel(repository, useFakeData = true)

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.allArticles!!,
        navigateToArticle = {},
        noArticlesDescStrResId = R.string.no_articles_desc,
    )
}
