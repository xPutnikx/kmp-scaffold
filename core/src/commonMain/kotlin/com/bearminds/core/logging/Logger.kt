package com.bearminds.core.logging

import io.github.aakira.napier.Napier

fun logD(tag: String = "", message: () -> String) {
    Napier.d(tag = tag, message = message())
}

fun logE(tag: String = "", throwable: Throwable? = null, message: () -> String) {
    Napier.e(tag = tag, throwable = throwable, message = message())
}

fun logW(tag: String = "", message: () -> String) {
    Napier.w(tag = tag, message = message())
}

fun logI(tag: String = "", message: () -> String) {
    Napier.i(tag = tag, message = message())
}
