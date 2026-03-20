package com.bearminds.scaffold

import androidx.compose.ui.window.ComposeUIViewController
import com.bearminds.scaffold.di.appModule
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
    val systemIsDark = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
        UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(systemIsDark = systemIsDark)
}
