package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

fun main(args: Array<String>) = runBlocking {
    run()
    delay(5000)
}

val numberOfWorkers = 10
val totalWork = 100

data class Work(var x: Long = 0, var y: Long = 0, var z: Long = 0)

suspend fun worker(input: Channel<Work>, output: Channel<Work>) {
    for (w in input) {
        w.z = w.x * w.y
        delay(w.z)
        output.send(w)
    }
}

fun run() {
    val input = Channel<Work>()
    val output = Channel<Work>()
    repeat(numberOfWorkers) {
        GlobalScope.launch {
            worker(input, output)
        }
    }
    GlobalScope.launch { sendLotsOfWork(input) }
    GlobalScope.launch { receiveLotsOfResult(output) }
}

suspend fun sendLotsOfWork(input: Channel<Work>) {
    repeat(totalWork) {
        input.send(Work((0L..100).random(), (0L..10).random()))
    }
}

suspend fun receiveLotsOfResult(output: Channel<Work>) {
    for (work in output) {
        println("The result is $work")
    }
}


private object RandomRange : Random()

fun ClosedRange<Long>.random() = RandomRange.nextInt((endInclusive.toInt() + 1) - start.toInt()) + start
