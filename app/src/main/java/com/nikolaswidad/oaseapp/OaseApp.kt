package com.nikolaswidad.oaseapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikolaswidad.oaseapp.ui.theme.OaseAppTheme

@Composable
fun OaseApp(
    modifier: Modifier = Modifier,
) {
//    HomeScreen(navigateToDetail = {newsId ->
//
//    })
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
//        HomeScreen(navigateToDetail = )
    }
}



@Preview(showBackground = true)
@Composable
fun OaseAppPreview() {
    OaseAppTheme {
        OaseApp()
    }
}