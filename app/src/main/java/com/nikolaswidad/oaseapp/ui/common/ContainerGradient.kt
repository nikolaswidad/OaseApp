package com.nikolaswidad.oaseapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolaswidad.oaseapp.ui.theme.GradientDark

@Composable
fun ContainerGradient() {
    Box(
        modifier = Modifier
            .width(width = 234.dp)
            .height(height = 171.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(brush = GradientDark)
    )
}

@Preview
@Composable
fun ContainerGradientPreview() {
    ContainerGradient()
}
