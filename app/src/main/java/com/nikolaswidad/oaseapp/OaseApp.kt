package com.nikolaswidad.oaseapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikolaswidad.oaseapp.ui.screen.home.HomeScreen
import com.nikolaswidad.oaseapp.ui.theme.OaseAppTheme

@Composable
fun OaseApp(
    modifier: Modifier = Modifier,
) {
    HomeScreen(navigateToDetail = {newsId ->

    })
}



@Preview(showBackground = true)
@Composable
fun OaseAppPreview() {
    OaseAppTheme {
        OaseApp()
    }
}