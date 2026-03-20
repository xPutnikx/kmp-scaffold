package com.bearminds.architecture.modifiers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.bottomBorder(
    color: Color = Color.LightGray,
    thickness: Dp = 1.dp,
): Modifier = this
    .drawWithContent {
        drawContent()
        drawLine(
            color = color,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = thickness.toPx()
        )
    }
