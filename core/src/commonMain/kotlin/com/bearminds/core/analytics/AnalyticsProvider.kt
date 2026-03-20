package com.bearminds.core.analytics

interface AnalyticsProvider {
    fun logEvent(name: String, params: Map<String, Any>? = null)
    fun setUserProperty(name: String, value: String?)
    fun setCollectionEnabled(enabled: Boolean)
}

class CompositeAnalytics(
    private val providers: MutableList<AnalyticsProvider> = mutableListOf()
) : AnalyticsProvider {

    override fun logEvent(name: String, params: Map<String, Any>?) {
        providers.forEach { it.logEvent(name, params) }
    }

    override fun setUserProperty(name: String, value: String?) {
        providers.forEach { it.setUserProperty(name, value) }
    }

    override fun setCollectionEnabled(enabled: Boolean) {
        providers.forEach { it.setCollectionEnabled(enabled) }
    }
}
