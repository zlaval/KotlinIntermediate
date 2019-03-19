package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main(args: Array<String>) = runBlocking {
    val side = Channel<Int>()
    val producer = produceNums(side)
    launch { side.consumeEach { println("side $it") } }
    producer.consumeEach {
        println("$it")
        delay(500)
    }

    val msg = producer()
    select<Unit> {
        msg.onReceive {
            println(it)
        }
        onTimeout(100) {
            println("Timed out")
        }
    }


}

fun produceNums(side: SendChannel<Int>) = GlobalScope.produce<Int> {
    for (num in 1..10) {
        delay(100)
        select<Unit> {
            onSend(num) {}
            side.onSend(num) {
            }
        }
    }
    println("Done sending")
}

fun producer() = GlobalScope.produce<Int> {
    var i = 0
    while (true) {
        delay(500)
        send(i++)
    }
}