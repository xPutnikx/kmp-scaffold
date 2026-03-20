package com.bearminds.core.network

import io.ktor.client.HttpClient

expect fun provideHttpClient(): HttpClient
