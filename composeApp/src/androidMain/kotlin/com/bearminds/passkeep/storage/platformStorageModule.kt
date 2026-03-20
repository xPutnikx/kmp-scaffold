package com.bearminds.passkeep.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformStorageModule = module {
    single<DataStore<Preferences>> {
        createPlatformDataStore(androidContext())
    }
}
