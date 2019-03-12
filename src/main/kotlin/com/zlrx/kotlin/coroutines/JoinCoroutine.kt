package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val job = GlobalScope.launch {
        println(" World")
    }
    print("Hello")
    job.join()
}