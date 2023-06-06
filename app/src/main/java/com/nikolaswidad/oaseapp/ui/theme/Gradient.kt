package com.nikolaswidad.oaseapp.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val GradientDark = Brush.linearGradient(0f to Color(0xff97afb9).copy(alpha = 0.6f),
    1f to Color(0xff006589).copy(alpha = 0.3f),
    start = Offset(40.43f, -62.34f),
    end = Offset(193.57f, 130.03f))

val GradientLight = Brush.linearGradient(
    0f to Color(0xff969696).copy(alpha = 0.4f),
    1f to Color(0xff969696).copy(alpha = 0.1f),
    start = Offset(7.84f, -18.23f),
    end = Offset(40.56f, 38.02f))