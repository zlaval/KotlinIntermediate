package com.zlrx.kotlin.coroutines.actor

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main(args: Array<String>) = runBlocking {
    val response = CompletableDeferred<Int>()
    val coordinator = coordinatorActor()
    coordinator.send(Start(response))
    response.await()
    println()
}

sealed class Move {
    override fun toString(): String {
        return javaClass.simpleName.toString()
    }
}

object Rock : Move()
object Paper : Move()
object Scissors : Move()

sealed class Game
class Start(val response: CompletableDeferred<Int>) : Game()
class Play(val sender: Channel<Game>, val name: String) : Game()
class Throw(val actor: String, val move: Move) : Game()

fun playerActor() = GlobalScope.actor<Game> {
    var name: String
    for (msg in channel) {
        when (msg) {
            is Play -> {
                name = msg.name
                val selection = (1..4).random()
                lateinit var move: Move
                when (selection) {
                    1 -> move = Rock
                    2 -> move = Paper
                    3 -> move = Scissors
                }
                msg.sender.send(Throw(name, move))
            }
        }
    }
}

fun coordinatorActor() = GlobalScope.actor<Game> {
    lateinit var respose: CompletableDeferred<Int>

    val player1 = playerActor()
    val player2 = playerActor()

    for (msg in channel) {
        when (msg) {
            is Start -> {
                respose = msg.response
                player1.send(Play(channel, "Player 1"))
                player2.send(Play(channel, "Player 2"))
            }
            is Throw -> {
                val playerA = msg.actor
                val moveA = msg.move
                val msg2 = channel.receive() as Throw
                val playerB = msg2.actor
                val moveB = msg2.move
                announce(playerA, moveA, playerB, moveB)
                respose.complete(0)
            }
        }
    }
}

fun announce(playerA: String, moveA: Move, playerB: String, moveB: Move) {
    println("$playerA move is $moveA, $playerB move is $moveB")
    if (moveA == moveB) {
        println("Draw")
    } else {
        var aWin = false
        when (moveA) {
            is Rock -> {
                if (moveB == Scissors) {
                    aWin = true
                }
            }
            is Paper -> {
                if (moveB == Rock) {
                    aWin = true
                }
            }
            is Scissors -> {
                if (moveB == Paper) {
                    aWin = true
                }
            }
        }
        println("${if (aWin) playerA else playerB} win")
    }
}

fun ClosedRange<Int>.random() = Random.nextInt(endInclusive - start) + start