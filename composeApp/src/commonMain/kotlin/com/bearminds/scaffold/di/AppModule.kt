package com.bearminds.scaffold.di

import com.bearminds.architecture.DefaultDispatcherProvider
import com.bearminds.architecture.DispatcherProvider
import com.bearminds.core.analytics.ConsentProvider
import com.bearminds.core.analytics.analyticsModule
import com.bearminds.core.connectivity.connectivityModule
import com.bearminds.core.network.networkModule
import com.bearminds.core.storage.storageModule
import com.bearminds.scaffold.root.view.HomeScreenDataBuilder
import com.bearminds.scaffold.root.vm.RootScreenViewModel
import com.bearminds.scaffold.storage.AppPreferences
import com.bearminds.scaffold.storage.AppPreferencesImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    includes(
        storageModule,
        networkModule,
        connectivityModule,
        analyticsModule,
    )

    single(named("applicationScope")) {
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
    }

    single<DispatcherProvider> { DefaultDispatcherProvider() }

    single<AppPreferences> { AppPreferencesImpl(get()) }
    single<ConsentProvider> { get<AppPreferences>() }

    factory { HomeScreenDataBuilder() }
    viewModel { RootScreenViewModel(dataBuilder = get()) }
}
