package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main(args: Array<String>) = runBlocking {
    val m1 = producer1()
    val m2 = producer2()
    repeat(15) {
        selector(m1, m2)
    }
}

suspend fun selector(message1: ReceiveChannel<String>, message2: ReceiveChannel<String>) {
    select<Unit> {
        message1.onReceiveOrNull {
            println(it)
        }
        message2.onReceiveOrNull {
            println(it)
        }
    }
}

fun producer1() = GlobalScope.produce {
    while (true) {
        //delay(200)
        send("producer 1")
    }
}

fun producer2() = GlobalScope.produce {
    while (true) {
        // delay(300)
        send("producer 2")
    }
}