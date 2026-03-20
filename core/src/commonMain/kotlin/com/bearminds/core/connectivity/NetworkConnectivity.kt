package com.bearminds.core.connectivity

import kotlinx.coroutines.flow.Flow

interface NetworkConnectivity {
    /**
     * Flow indicating whether the device is online.
     * - `null` = unknown (not yet determined)
     * - `true` = online
     * - `false` = offline
     */
    val isOnline: Flow<Boolean?>

    suspend fun isCurrentlyOnline(): Boolean?

    fun startMonitoring()

    fun stopMonitoring()
}
