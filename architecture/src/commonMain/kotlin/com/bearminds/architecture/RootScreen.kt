package com.bearminds.architecture

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    contentWindowInsets: WindowInsets = WindowInsets.systemBars,
    showToolbar: Boolean = true,
    title: @Composable () -> Unit = {},
    effects: Flow<BaseViewModel.ViewEffect>? = null,
    onNavigateBack: (() -> Unit)? = null,
    backAction: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    bottomBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    fabPosition: FabPosition = FabPosition.End,
    actions: @Composable RowScope.() -> Unit = {},
    topBarRightPadding: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = containerColor,
        topBar = if (showToolbar) {
            {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = containerColor,
                    ),
                    title = title,
                    navigationIcon = {
                        backAction?.invoke()
                    },
                    actions = actions,
                    windowInsets = TopAppBarDefaults.windowInsets.add(WindowInsets(right = topBarRightPadding))
                )
            }
        } else {
            {}
        },
        contentWindowInsets = contentWindowInsets,
        bottomBar = bottomBar,
        floatingActionButton = fab,
        floatingActionButtonPosition = fabPosition,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        actionContentColor = MaterialTheme.colorScheme.primary,
                        dismissActionContentColor = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
                )
                .consumeWindowInsets(innerPadding)
        ) {
            content()
        }
    }
    val hapticFeedback = LocalHapticFeedback.current
    if (effects != null) {
        EffectProcessor(snackbarHostState, hapticFeedback, effects, onNavigateBack)
    }
}


@Composable
private fun EffectProcessor(
    snackbarHostState: SnackbarHostState,
    hapticFeedback: androidx.compose.ui.hapticfeedback.HapticFeedback,
    effectFlow: Flow<BaseViewModel.ViewEffect>,
    onNavigateBack: (() -> Unit)?,
) {
    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is BaseViewModel.NavigateBackEffect -> {
                    onNavigateBack?.invoke()
                }

                is BaseViewModel.SnackbarEffect -> {
                    launch {
                        snackbarHostState.showSnackbar(
                            message = effect.data.message,
                            actionLabel = effect.data.actionLabel,
                            duration = effect.data.duration,
                            withDismissAction = effect.data.withDismissAction
                        )
                    }
                }

                is BaseViewModel.SnackbarResourceEffect -> {
                    launch {
                        snackbarHostState.showSnackbar(
                            message = getString(effect.data.messageRes),
                            actionLabel = effect.data.actionLabel,
                            duration = effect.data.duration,
                            withDismissAction = effect.data.withDismissAction
                        )
                    }
                }

                is BaseViewModel.HapticFeedbackEffect -> {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            }
        }
    }
}
