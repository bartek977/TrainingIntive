package com.example.trainingintive.util

import com.example.trainingintive.R
import java.net.UnknownHostException

object ErrorMessageId {

    fun getId(exception: Exception) =
        when (exception) {
            is UnknownHostException -> R.string.internet_connection_error
            else -> R.string.unkonwn_error
        }
}

// TODO It could be changed to extension, object is redundant here
fun Exception.toErrorTextId() =
    when (this) {
        is UnknownHostException -> R.string.internet_connection_error
        else -> R.string.unkonwn_error
    }
