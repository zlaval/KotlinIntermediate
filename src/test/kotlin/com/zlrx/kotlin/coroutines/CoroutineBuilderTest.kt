package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class SimpleTest {

    @Test
    fun testCoroutine() = runBlocking {
        doWork()
    }


}