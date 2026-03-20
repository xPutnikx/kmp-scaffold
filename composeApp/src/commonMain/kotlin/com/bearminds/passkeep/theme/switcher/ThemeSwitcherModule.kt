package com.bearminds.passkeep.theme.switcher

import com.bearminds.passkeep.storage.AppPreferences
import com.bearminds.passkeep.storage.storageModule
import com.bearminds.passkeep.theme.switcher.vm.ThemeSwitcherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val themeSwitcherModule = module {
    includes(storageModule)

    viewModel {
        ThemeSwitcherViewModel(get<AppPreferences>())
    }
}
