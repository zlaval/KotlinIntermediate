package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val one = async {
        doWorkOne()
    }

    val two = async {
        doWorkTwo()
    }

    val res1 = one.await()
    val res2 = two.await()
    println("result= ${res1 + res2}")
}

suspend fun doWorkOne(): Int {
    delay(100)
    println("Work1")
    return 1
}

suspend fun doWorkTwo(): Int {
    delay(100)
    println("Work2")
    return 2
}