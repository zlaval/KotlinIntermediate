package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking(Dispatchers.Default) {
    val result = async(coroutineContext, start = CoroutineStart.LAZY) { doWorkLazy() }
    println(result.await())
}

suspend fun doWorkLazy(): Int {
    delay(100)
    println("WorkLazy")
    println(Thread.currentThread().name)
    return 42
}

