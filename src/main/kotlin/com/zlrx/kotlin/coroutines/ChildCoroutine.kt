package com.zlrx.kotlin.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) = runBlocking {

    val outer = launch {

        launch(coroutineContext) {
            try {
                println("inner isActive: ${coroutineContext[Job.Key]!!.isActive}")
                repeat(1000) {
                    print(".")
                    delay(1)
                }
            } catch (e: CancellationException) {
                println("Inner is cancelled")
            }
        }
        delay(3000)


//            GlobalScope.launch {
//                repeat(10) {
//                    print("X")
//                    delay(1)
//                }
//            }

    }

    //outer.join()

    delay(100)
    //outer.join()
    //outer.cancelAndJoin()
    outer.cancelChildren()
    println()
    println("Outer is cancelled? ${outer.isCancelled}")
    //delay(1000)
    println()
    println("Finished")

}