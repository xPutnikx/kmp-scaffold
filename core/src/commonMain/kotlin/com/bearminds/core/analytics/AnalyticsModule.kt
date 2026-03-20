package com.bearminds.core.analytics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.dsl.module

val analyticsModule = module {
    single<AnalyticsProvider> {
        val provider = CompositeAnalytics(
            createPlatformAnalyticsProviders().toMutableList()
        )

        val consentProvider: ConsentProvider = get()
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        consentProvider.isAnalyticsEnabled
            .onEach { enabled -> provider.setCollectionEnabled(enabled) }
            .launchIn(scope)

        provider
    }
}
