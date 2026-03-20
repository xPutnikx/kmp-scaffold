package com.bearminds.passkeep.di

import com.bearminds.passkeep.AppViewModel
import com.bearminds.passkeep.root.rootModule
import com.bearminds.passkeep.storage.AppPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(rootModule)

    single(named("applicationScope")) {
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
    }

    viewModel {
        AppViewModel(
            get<CoroutineScope>(named("applicationScope")),
            get<AppPreferences>()
        )
    }
}
