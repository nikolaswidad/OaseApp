package com.nikolaswidad.oaseapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nikolaswidad.oaseapp.R
import com.nikolaswidad.oaseapp.ui.theme.OaseAppTheme
import com.nikolaswidad.oaseapp.ui.theme.Shapes

@Composable
fun LargeNewsItem(
//    newsId: String,
    title: String,
    photoUrl: String,
    source: String,
    timestamp: String,
    isBookmarked: Boolean,
    modifier: Modifier = Modifier
) {
        // Row News
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(all = 10.dp)
            ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(Shapes.small)

            )
            Spacer(
                modifier = Modifier
                    .height(height = 8.dp))
            // Kolom Fitur
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Row Sentiment Start
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_sentiment_positive),
                        contentDescription = null,
                        modifier = Modifier

                    )
                    Spacer(
                        modifier = Modifier
                            .width(width = 2.dp)
                    )
                    Text(
                        text = "Sentiment",
                        style = MaterialTheme.typography.headlineSmall.copy(
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
                // Row Sentiment End

                // Row Verified Start
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_credibility_verified),
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier
                            .width(width = 2.dp)
                    )
                    Text(
                        text = "82%",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .width(width = 2.dp)
                    )
                    Column {
                        Text(
                            text = "Accurate",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 7.sp,
                            )
                        )
                        Text(
                            text = "Score",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontSize = 7.sp
                            ),
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(height = 8.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(height = 80.dp)
                )
                Image(
                    painter = painterResource(R.drawable.ic_bookmark),
                    contentDescription = "Icons/bookmark_24px"
                )
            }


            Spacer(
                modifier = Modifier
                    .height(height = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = source,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Light
                    )
                )
                Text(
                    text = timestamp,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }

}

@Composable
fun NewsItem(
    newsId: Long,
    title: String,
    photoUrl: String,
    source: String,
    publishedAt: String,
    verified: String,
    verifiedPercentage: Int,
//    sentiment: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        // Row News
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
                    .weight(1.0f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = source,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Light
                        )
                    )
                    Text(
                        text = publishedAt,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Light
                        )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(height = 10.dp)
                )
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 100.dp)
                )
            }
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(Shapes.small)

            )
        }
        Spacer(
            modifier = Modifier
                .height(height = 8.dp)
        )
        // Kolom Fitur
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Row Sentiment Start
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_sentiment_positive),
                    contentDescription = null,
                    modifier = Modifier

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
            // Row Sentiment End

            // Row Verified Start
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_credibility_verified),
                    contentDescription = null
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 2.dp)
                )
                Text(
                    text = "${verifiedPercentage}%",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(
                    modifier = Modifier
                        .width(width = 2.dp)
                )
                Column {
                    Text(
                        text = stringResource(R.string.tv_accurate_score),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 7.sp,
                            lineHeight = 8.sp
                        ),
                    )
                }
            }
            var isBookmarked = false

            BookmarkButton(
                modifier = Modifier.size(24.dp),
                isBookmarked = isBookmarked,
                onClick = { isBookmarked = !isBookmarked }
            )
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun LargeCardItemPreview() {
//    OaseAppTheme() {
//        LargeNewsItem(
//            photoUrl = "",
//            title = "Large Card",
//            source = "Harian",
//            timestamp = "2 Hours Ago"
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    OaseAppTheme() {
        NewsItem(
        1,
        "Jokowi Pastikan Ibu Kota Baru Tawarkan Konsep Kota Smart Metropolis",
        "R.drawable.news_pro",
        "KOMPAS",
        "7 Hours Ago",
        "Real",
        88,
        )
    }
}

