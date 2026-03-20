package com.bearminds.core.connectivity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URI

class JvmNetworkConnectivity(
    private val healthCheckUrl: String = "https://clients3.google.com/generate_204",
) : NetworkConnectivity {

    private val _isOnline = MutableStateFlow<Boolean?>(checkReachability())
    override val isOnline: Flow<Boolean?> = _isOnline.asStateFlow()

    private var scope: CoroutineScope? = null

    override suspend fun isCurrentlyOnline(): Boolean? = _isOnline.value

    override fun startMonitoring() {
        if (scope != null) return

        val pollingScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        scope = pollingScope

        pollingScope.launch {
            while (isActive) {
                _isOnline.value = checkReachability()
                delay(POLL_INTERVAL_MS)
            }
        }
    }

    override fun stopMonitoring() {
        scope?.cancel()
        scope = null
    }

    private fun checkReachability(): Boolean {
        return try {
            val connection = URI(healthCheckUrl).toURL().openConnection() as HttpURLConnection
            connection.requestMethod = "HEAD"
            connection.connectTimeout = TIMEOUT_MS
            connection.readTimeout = TIMEOUT_MS
            connection.useCaches = false
            val responseCode = connection.responseCode
            connection.disconnect()
            responseCode in 200..299
        } catch (_: Exception) {
            false
        }
    }

    companion object {
        private const val POLL_INTERVAL_MS = 5_000L
        private const val TIMEOUT_MS = 3_000
    }
}
