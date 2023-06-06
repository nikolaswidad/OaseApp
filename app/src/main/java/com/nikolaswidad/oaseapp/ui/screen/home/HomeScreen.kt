package com.nikolaswidad.oaseapp.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikolaswidad.oaseapp.di.Injection
import com.nikolaswidad.oaseapp.model.FakeNewsDataSource
import com.nikolaswidad.oaseapp.model.NewsBookmark
import com.nikolaswidad.oaseapp.ui.ViewModelFactory
import com.nikolaswidad.oaseapp.ui.common.UiState
import com.nikolaswidad.oaseapp.ui.components.NewsItem
import com.nikolaswidad.oaseapp.ui.theme.OaseAppTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState -> 
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllNews()
            }
            is UiState.Success -> {
                HomeContent(
                    newsBookmark = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    newsBookmark: List<NewsBookmark>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
//    LazyColumn(
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ){
////        item {
////            SearchBox()
////            Spacer(
////                modifier = Modifier
////                    .height(height = 10.dp)
////            )
////        }
//        item {
//            Text(
//                text = "Top News",
//                overflow = TextOverflow.Ellipsis,
//                style = MaterialTheme.typography.headlineLarge.copy(
//                    fontSize = 40.sp,
//                    fontWeight = FontWeight.Bold
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(height = 100.dp)
//            )
//        }
//
//    }
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember{
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(FakeNewsDataSource.dummyNews, key = { it.id }) { news ->
                Column {
                    NewsItem(
                        newsId = news.id,
                        title = news.title,
                        photoUrl = news.photoUrl,
                        source = news.source,
                        publishedAt = news.publishedAt,
                        verified = news.publishedAt,
                        verifiedPercentage = news.verifiedPercentage
                    )
                    Divider(color = Color.Gray, thickness = 0.2.dp)
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Composable
fun ScrollToTopButton(
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .shadow(elevation = 10.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .size(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun OaseAppPreview() {
//    OaseAppTheme {
//        HomeScreen()
//    }
//}
