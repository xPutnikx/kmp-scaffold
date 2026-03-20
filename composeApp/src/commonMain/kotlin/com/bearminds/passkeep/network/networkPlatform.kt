package com.bearminds.passkeep.network

import io.ktor.client.HttpClient

expect fun provideHttpClient(): HttpClient
