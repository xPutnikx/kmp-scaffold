package com.bearminds.scaffold.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

private const val SLIDE_DURATION = 300

fun rightToLeftEnter(): EnterTransition = slideInHorizontally(
    animationSpec = tween(SLIDE_DURATION),
    initialOffsetX = { it }
)

fun leftToRightExit(): ExitTransition = slideOutHorizontally(
    animationSpec = tween(SLIDE_DURATION),
    targetOffsetX = { it }
)

fun subtleLeftExit(): ExitTransition = slideOutHorizontally(
    animationSpec = tween(SLIDE_DURATION),
    targetOffsetX = { -it / 4 }
) + fadeOut(animationSpec = tween(SLIDE_DURATION))

fun subtleLeftEnter(): EnterTransition = slideInHorizontally(
    animationSpec = tween(SLIDE_DURATION),
    initialOffsetX = { -it / 4 }
) + fadeIn(animationSpec = tween(SLIDE_DURATION))
