package com.nikolaswidad.oaseapp.ui.screen.news

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.nikolaswidad.oaseapp.domain.model.Article


@Composable
fun NewsScreen(news: Article) {
    NewsWebView(url = news.url)
}

@Composable
private fun NewsWebView(url: String) {

    if (url.isEmpty()){
        return
    }

    Column {
        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })
    }
}