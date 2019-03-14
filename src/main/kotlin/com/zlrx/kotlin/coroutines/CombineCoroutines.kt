package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

//-Dkotlinx.coroutines.debug
fun main(args: Array<String>) = runBlocking(newFixedThreadPoolContext(4, "MyPool")) {

    println(coroutineContext)
    println("Thread: ${Thread.currentThread().name}")

    val job = launch(coroutineContext + CoroutineName("First")) {
        println(coroutineContext)
        println("Launch thread: ${Thread.currentThread().name}")
    }

    job.join()
}