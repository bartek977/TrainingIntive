package com.example.trainingintive.util

import com.example.trainingintive.R
import java.net.UnknownHostException

fun Throwable.toErrorTextId() =
    when (this) {
        is UnknownHostException -> R.string.internet_connection_error
        else -> R.string.unknown_error
    }
