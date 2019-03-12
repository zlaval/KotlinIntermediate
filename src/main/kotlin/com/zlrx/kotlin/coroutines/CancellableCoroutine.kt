package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {

    val job = GlobalScope.launch {
        repeat(10000) {
            if (!isActive) return@launch //throw CancellationException()
            print(".")
            // yield()
        }
    }

    delay(1)
    job.cancelAndJoin()
    println("done")

}