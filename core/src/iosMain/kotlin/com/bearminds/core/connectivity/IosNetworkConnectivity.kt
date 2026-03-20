package com.bearminds.core.connectivity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_get_status
import platform.Network.nw_path_status_satisfied
import platform.Network.nw_path_monitor_t
import platform.darwin.dispatch_queue_create

class IosNetworkConnectivity : NetworkConnectivity {

    private val _isOnline = MutableStateFlow<Boolean?>(null)
    override val isOnline: Flow<Boolean?> = _isOnline.asStateFlow()

    private var monitor: nw_path_monitor_t? = null

    override suspend fun isCurrentlyOnline(): Boolean? = _isOnline.value

    override fun startMonitoring() {
        if (monitor != null) return

        val pathMonitor = nw_path_monitor_create()
        val queue = dispatch_queue_create("com.bearminds.network.monitor", null)

        nw_path_monitor_set_queue(pathMonitor, queue)
        nw_path_monitor_set_update_handler(pathMonitor) { path ->
            _isOnline.value = nw_path_get_status(path) == nw_path_status_satisfied
        }
        nw_path_monitor_start(pathMonitor)

        monitor = pathMonitor
    }

    override fun stopMonitoring() {
        monitor?.let {
            nw_path_monitor_cancel(it)
            monitor = null
        }
    }
}
