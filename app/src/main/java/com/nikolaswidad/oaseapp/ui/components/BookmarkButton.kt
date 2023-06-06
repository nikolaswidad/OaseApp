package com.nikolaswidad.oaseapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikolaswidad.oaseapp.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BookmarkButton(
    modifier: Modifier = Modifier,
    isBookmarked: Boolean,
    onClick: () -> Unit
) {
    val bookmarkIconRes = if (isBookmarked) {
        R.drawable.ic_bookmarked
    } else {
        R.drawable.ic_bookmark
    }

    Image(
        painter = painterResource(bookmarkIconRes),
        contentDescription = "Bookmark Icon",
        modifier = modifier.clickable(onClick = onClick)
    )
}


@Preview
@Composable
fun BookmarkButtonPreview() {
    // change the boolean
    var isBookmarked = false

    BookmarkButton(
        modifier = Modifier.size(24.dp),
        isBookmarked = isBookmarked,
        onClick = { isBookmarked = !isBookmarked }
    )
}
//@Composable
//fun BookmarkButton(isBookmarked: Boolean) {
//    val bookmarkIconRes = if (isBookmarked) {
//        R.drawable.ic_bookmark_true
//    } else {
//        R.drawable.ic_bookmark_false
//    }
//
//    Image(
//        painter = painterResource(bookmarkIconRes),
//        contentDescription = "Bookmark Icon"
//    )
//}

//@Preview
//@Composable
//fun BookmarkButtonPreview() {
//    var isBookmarked by remember { mutableStateOf(false) }
//
//    BookmarkButton(
//        modifier = Modifier.size(24.dp),
//        isBookmarked = isBookmarked,
//        onClick = { isBookmarked = !isBookmarked }
//    )
//}

