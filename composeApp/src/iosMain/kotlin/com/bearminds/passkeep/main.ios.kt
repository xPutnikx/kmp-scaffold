package com.bearminds.passkeep

import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.bearminds.passkeep.di.appModule
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.startKoin
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController

@Suppress("unused", "FunctionName")
fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule)
        }
    }
) {
    val systemStyle = UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    val viewModel: AppViewModel = koinViewModel<AppViewModel>()
    App(Modifier, systemStyle, viewModel)
}
