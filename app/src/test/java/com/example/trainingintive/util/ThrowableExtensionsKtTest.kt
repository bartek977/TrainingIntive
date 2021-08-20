package com.example.trainingintive.util

import com.example.trainingintive.R
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.net.UnknownHostException

class ThrowableExtensionsKtTest {

    @Test
    fun testUnknownHostExceptionMessage() {
        val given = UnknownHostException()

        val tested = given.toErrorTextId()

        tested shouldBeEqualTo R.string.internet_connection_error
    }

    @Test
    fun testGeneralExceptionMessage() {
        val given = Exception()

        val tested = given.toErrorTextId()

        tested shouldBeEqualTo R.string.unknown_error
    }
}
