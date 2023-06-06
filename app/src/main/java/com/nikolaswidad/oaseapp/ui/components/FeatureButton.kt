package com.nikolaswidad.oaseapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikolaswidad.oaseapp.R
import com.nikolaswidad.oaseapp.ui.theme.GradientDark
import com.nikolaswidad.oaseapp.ui.theme.GradientLight
import com.nikolaswidad.oaseapp.ui.theme.Shapes

@Composable
fun FeatureButton(
//    newsId: Long,
//    onLike: ()->Unit,
//    onComment: ()->Unit,
//    onSummary: ()->Unit,
//    onCompare: ()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(15.dp)
                .size(100.dp, 30.dp)
                .clip(shape = Shapes.small)
                .background(brush = GradientDark)
                .border(border = BorderStroke(2.dp, GradientLight))
                .clickable {

                }
        ) {
            Text(
                text = "Summarize",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp),
//                modifier = Modifier
//                    .border(border = BorderStroke(2.dp, GradientLight))

            )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
//            modifier = modifier
//    //            .size(width = 50.dp, height = 50.dp)
//                .padding(15.dp)
        ) {
            Surface(
                shape = Shapes.large,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                color = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_like),
                    contentDescription = "like",
                    modifier = Modifier.padding(20.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,

    //            shape = RoundedCornerShape(size = 5.dp),
    //            border = BorderStroke(1.dp, Color.DarkGray),
    //            contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(20.dp)
                    .size(100.dp)
                    .clip(shape = Shapes.small)
                    .border(border = BorderStroke(2.dp, GradientLight))
                    .background(brush = GradientDark)
                    .clickable {

                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_compare),
                    contentDescription = "compare",
                    tint = Color.White,
                    modifier = Modifier.padding(20.dp,15.dp)
                )
            }
            Surface(
                shape = RoundedCornerShape(size = 10.dp),
                border = BorderStroke(1.dp, Color.DarkGray),
                color = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(80.dp)
                    .clickable{

                    }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_comment),
                    contentDescription = "comment",
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FeatureButtontPreview() {
    FeatureButton()
}

