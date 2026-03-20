package com.bearminds.passkeep

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform