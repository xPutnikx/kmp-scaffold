package com.bearminds.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformStorageModule: Module = module {
    single<DataStore<Preferences>> {
        createDataStore(
            producePath = {
                androidContext().filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
            }
        )
    }
}
