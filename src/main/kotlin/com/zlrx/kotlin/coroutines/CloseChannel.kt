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
        channel.close()
    }

//    repeat(8) {
//        val value = channel.receive()
//        println("receive $value")
//    }

    for (value in channel) {
        println("receive $value")
    }

    job.join()

}
