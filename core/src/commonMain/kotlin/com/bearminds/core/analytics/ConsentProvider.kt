package com.bearminds.core.analytics

import kotlinx.coroutines.flow.Flow

interface ConsentProvider {
    val isAnalyticsEnabled: Flow<Boolean>
}
