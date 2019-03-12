package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {
    val job = GlobalScope.launch {
        try {
            repeat(10000) {
                yield()
                print(".")
                Thread.sleep(10)
            }
        } catch (ex: CancellationException) {
            println()
            println("Cancelled: ${ex.message}")
        } finally {
            withContext(NonCancellable) {
                println()
                println("Finally")
            }

        }

    }
    delay(100)
    job.cancelAndJoin()
    println("done")
}