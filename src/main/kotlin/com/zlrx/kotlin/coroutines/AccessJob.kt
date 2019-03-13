package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val job = launch {
        println("isActive: ${coroutineContext[Job.Key]!!.isActive}")
    }
    job.join()
}