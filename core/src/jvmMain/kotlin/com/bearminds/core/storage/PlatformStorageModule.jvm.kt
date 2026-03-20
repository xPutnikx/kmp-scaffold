package com.bearminds.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformStorageModule: Module = module {
    single<DataStore<Preferences>> {
        val path = appDataPath(appName = "KmpScaffold", fileName = DATA_STORE_FILE_NAME)
        FileSystem.SYSTEM.createDirectories(path.parent!!)
        createDataStore(producePath = { path.toString() })
    }
}

private fun appDataPath(appName: String, fileName: String): Path {
    val home = System.getProperty("user.home")
    val os = System.getProperty("os.name").lowercase()

    val base: Path = when {
        os.contains("win") -> {
            val local = System.getenv("LOCALAPPDATA") ?: "$home\\AppData\\Local"
            "$local\\$appName".toPath()
        }
        os.contains("mac") -> {
            "$home/Library/Application Support/$appName".toPath()
        }
        else -> {
            val xdg = System.getenv("XDG_CONFIG_HOME") ?: "$home/.config"
            "$xdg/$appName".toPath()
        }
    }

    return base / fileName
}
