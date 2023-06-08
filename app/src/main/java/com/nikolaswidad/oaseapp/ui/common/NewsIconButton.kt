package com.nikolaswidad.oaseapp.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.nikolaswidad.oaseapp.domain.model.Article

@Composable
fun NewsIconButton(
    news: Article,
    onIconClick: (Int) -> Unit,
    iconPainter: Painter,
    contentDescription: String? = null
) {
    IconButton(onClick = { onIconClick(news.id) }) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription
        )
    }
}