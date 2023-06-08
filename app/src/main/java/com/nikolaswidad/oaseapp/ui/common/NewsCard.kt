package com.nikolaswidad.oaseapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
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
import com.nikolaswidad.oaseapp.domain.model.Article
import com.nikolaswidad.oaseapp.ui.theme.PaddingMedium
import com.nikolaswidad.oaseapp.ui.theme.PaddingSmall

@Composable
fun NewsCard(
    news: Article,
    onNewsCardClick: (Int) -> Unit,
    onBookmarkClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingMedium)
    ) {
        NewsContent(news, onNewsCardClick)
        Spacer(Modifier.padding(PaddingSmall))

        NewsBottomRow(news, onBookmarkClick)
        Spacer(Modifier.padding(PaddingSmall))

        Divider(thickness = 2.dp)
    }
}

@Composable
fun NewsContent(
    news: Article,
    onNewsCardClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(end = PaddingSmall)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = news.source)
            
            Text(text = news.publishedAt)
        }
        Text(text = news.title, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun NewsImage(news: Article) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(news.image),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun NewsBottomRow(
    news: Article,
//    onSentimentClick: (Int) -> Unit,
    onBookmarkClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SentimentIconButton(
            news = news,
            iconPainter = when (news.sentiment) {
                1 -> painterResource(R.drawable.ic_sentiment_negative)
                2 -> painterResource(R.drawable.ic_sentiment_neutral)
                else -> painterResource(R.drawable.ic_sentiment_positive)
            }
        )
        CredibilityRow(
            news = news,
            iconPainter = if (news.credibility == 0)
                painterResource(R.drawable.ic_credibility_warning)
            else
                painterResource(R.drawable.ic_credibility_verified)
        )
        NewsIconButton(
            news = news,
            onIconClick = onBookmarkClick,
            iconPainter = if (news.bookmarked)
                    painterResource(R.drawable.ic_bookmarked)
                else
                    painterResource(R.drawable.ic_bookmark)
        )
    }
}

@Composable
fun SentimentIconButton(
    news: Article,
    iconPainter: Painter,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = iconPainter,
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

@Composable
fun CredibilityRow(
    news: Article,
    iconPainter: Painter,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.tv_sentiment),
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Icon(
            painter = painterResource(R.drawable.ic_info),
            contentDescription = "info",
            modifier = Modifier
                .size(size = 12.dp)
        )
    }
}