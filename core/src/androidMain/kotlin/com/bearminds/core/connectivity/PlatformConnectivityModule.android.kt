package com.bearminds.core.connectivity

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformConnectivityModule: Module = module {
    single<NetworkConnectivity> { AndroidNetworkConnectivity(androidContext()) }
}
