package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

const val THRESHOLD = 5000

suspend fun compute(array: IntArray, low: Int, high: Int): Long {
    return if (high - low <= THRESHOLD) {
        println("$low to $high Calculate on thread ${Thread.currentThread().name}")
        (low until high)
            .map { array[it].toLong() }
            .sum()
    } else {
        val mid = low + (high - low) / 2
        val left = GlobalScope.async { compute(array, low, mid) }
        val right = compute(array, mid, high)
        return left.await() + right
    }
}

fun main(args: Array<String>) = runBlocking {
    val list = (0..20_000_000).toList().toIntArray()
    var result = 0L
    val time = measureTimeMillis {
        result = compute(list, 0, list.size)
    }
    println("$result in $time ms")
}