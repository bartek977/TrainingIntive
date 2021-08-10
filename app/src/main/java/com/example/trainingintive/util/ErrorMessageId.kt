package com.example.trainingintive.util

import com.example.trainingintive.R
import java.lang.Exception
import java.net.UnknownHostException

object ErrorMessageId {

    fun getId(exception: Exception) =
        when (exception) {
            is UnknownHostException -> R.string.internet_connection_error
            else -> R.string.unkonwn_error
        }
}
