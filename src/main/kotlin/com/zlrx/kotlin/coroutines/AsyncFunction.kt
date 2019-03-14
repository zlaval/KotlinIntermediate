package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) {
    val result = doWorkAsync()
    runBlocking { println(result.await()) }

}

fun doWorkAsync(): Deferred<Int> = GlobalScope.async {
    delay(100)
    println("WorkAsync")
    return@async 42
}

