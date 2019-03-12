package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {

    val job = GlobalScope.launch {
        repeat(1000) {
            delay(100)
            print(".")
        }
    }

    delay(2000)
//    job.cancel()
//    job.join()
    job.cancelAndJoin()
    println("done")
}