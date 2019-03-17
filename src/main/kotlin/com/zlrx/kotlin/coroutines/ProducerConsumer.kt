package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val channel = produceNumber()
    channel.consumeEach { println("receive $it") }

}

fun produceNumber(): ReceiveChannel<Int> = GlobalScope.produce {
    for (i in 1..5) {
        println("send $i")
        send(i)
    }
    println("Done")
}