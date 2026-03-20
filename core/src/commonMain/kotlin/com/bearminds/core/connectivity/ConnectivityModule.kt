package com.bearminds.core.connectivity

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformConnectivityModule: Module

val connectivityModule = module {
    includes(platformConnectivityModule)
}
