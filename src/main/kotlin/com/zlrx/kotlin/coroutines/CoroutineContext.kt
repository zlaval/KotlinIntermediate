package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*


fun main(args: Array<String>) = runBlocking {
    val jobs = GlobalScope.createJobs()
    jobs.forEach { it.join() }

    val job = launch(Dispatchers.Default) {
        println()
        println(" in main fun: ${Thread.currentThread().name}")
        val jobsInLaunch = GlobalScope.createJobs()
        jobsInLaunch.forEach { it.join() }
    }
    job.join()

}

private fun GlobalScope.createJobs(): ArrayList<Job> {
    val jobs = arrayListOf<Job>()
    jobs += launch {
        println("default in thread: ${Thread.currentThread().name}")
    }
    jobs += launch(Dispatchers.Default) {
        println("Dispatchers.Default in thread: ${Thread.currentThread().name}")
    }
    jobs += launch(Dispatchers.Unconfined) {
        println("1. Dispatchers.Unconfined in thread: ${Thread.currentThread().name}")
        delay(10)
        println("2. Dispatchers.Unconfined in thread: ${Thread.currentThread().name}")
        yield()
        println("3. Dispatchers.Unconfined in thread: ${Thread.currentThread().name}")
    }
    jobs += launch(coroutineContext) {
        println("coroutineContext in thread: ${Thread.currentThread().name}")
    }
    jobs += launch(newSingleThreadContext("ZLRX")) {
        println("newSingleThreadContext in thread: ${Thread.currentThread().name}")
    }
    return jobs
}