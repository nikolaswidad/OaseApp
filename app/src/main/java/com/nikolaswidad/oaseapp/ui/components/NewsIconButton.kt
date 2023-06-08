package com.nikolaswidad.oaseapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.nikolaswidad.oaseapp.model.NewsUi

@Composable
fun NewsIconButton(
    newsUi: NewsUi,
    onIconClick: (NewsUi) -> Unit,
    iconPainter: Painter,
    contentDescription: String? = null
) {
    IconButton(onClick = { onIconClick(newsUi) }) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription
        )
    }
}