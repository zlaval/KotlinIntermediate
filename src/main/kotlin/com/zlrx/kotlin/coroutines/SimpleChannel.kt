package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {

    val channel = Channel<Int>()

    val job = launch {
        for (i in 1..5) {
            println("send $i")
            channel.send(i)
        }

    }

    repeat(5) {
        val value = channel.receive()
        println("receive $value")
    }

    job.join()

}