package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.yield

fun main(args: Array<String>) = runBlocking {
    val job = withTimeoutOrNull(100) {
        repeat(10000) {
            yield()
            print(".")
            Thread.sleep(10)
        }
    }
    delay(100)
    if (job == null) {
        println("Timed out")
    }

    println("done")
}