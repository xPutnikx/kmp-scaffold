package com.bearminds.passkeep.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.dsl.module

val storageModule = module {
    includes(platformStorageModule)

    single<AppPreferences> { AppPreferencesImpl(get<DataStore<Preferences>>()) }
}
