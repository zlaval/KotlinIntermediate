package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val producer = produceNumbers()
    val square = squareNumbers(producer)
    for (i in 1..20) {
        println("receive ${square.receive()}")
    }
    square.cancel()
    producer.cancel()

}

fun produceNumbers(): ReceiveChannel<Int> = GlobalScope.produce {
    var x = 0
    while (true) {
        send(x++)
    }
}

fun squareNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = GlobalScope.produce {
    for (x in numbers) {
        send(x * x)
    }
}