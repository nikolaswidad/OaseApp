package com.nikolaswidad.oaseapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nikolaswidad.oaseapp.R
import com.nikolaswidad.oaseapp.model.NewsUi
import com.nikolaswidad.oaseapp.ui.theme.PaddingSmall

@Composable
fun NewsContent(newsUi: NewsUi) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(end = PaddingSmall)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = newsUi.source)
            
            Text(text = newsUi.publishedAt)
        }
        Text(text = newsUi.title, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NewsImage(newsUi: NewsUi) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(newsUi.image),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun NewsBottomRow(
    newsUi: NewsUi,
    onSentimentClick: (NewsUi) -> Unit,
    onBookmarkClick: (NewsUi) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SentimentIconButton(onClick = onSentimentClick)
        NewsIconButton(
            newsUi = newsUi,
            onIconClick = onBookmarkClick,
            iconPainter = painterResource(R.drawable.ic_bookmark)
        )
    }
}

@Composable
fun SentimentIconButton(
    onClick: (NewsUi) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_positive),
            contentDescription = null,
        )
        Spacer(
            modifier = Modifier
                .width(width = 2.dp)
        )
        Text(
            text = stringResource(R.string.tv_sentiment),
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Spacer(
            modifier = Modifier
                .width(width = 2.dp)
        )
        Icon(
            painter = painterResource(R.drawable.ic_info),
            contentDescription = "info",
            modifier = Modifier
                .size(size = 12.dp)
        )
    }
}