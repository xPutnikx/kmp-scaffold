package com.bearminds.passkeep.root

import com.bearminds.passkeep.network.networkModule
import com.bearminds.passkeep.root.vm.RootScreenViewModel
import com.bearminds.passkeep.storage.storageModule
import com.bearminds.passkeep.theme.switcher.themeSwitcherModule
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    includes(
        storageModule,
        networkModule,
        themeSwitcherModule
    )

    viewModel {
        RootScreenViewModel()
    }
}
