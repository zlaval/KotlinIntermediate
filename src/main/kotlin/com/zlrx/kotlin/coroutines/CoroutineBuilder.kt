package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    GlobalScope.launch {
        delay(1000)
        println(" World")
    }
    print("Hello")
    doWork()
}

suspend fun doWork() = delay(2000)