package com.nikolaswidad.oaseapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolaswidad.oaseapp.R

@Composable
fun FeatureButtonVariant(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 50.dp, height = 50.dp)
            .padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            color = Color.White,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_like),
                contentDescription = "like")
        }
    }
}

@Preview
@Composable
fun FeatureButtonVariantPreview() {
    FeatureButtonVariant()
}

