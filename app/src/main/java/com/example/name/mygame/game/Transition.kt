package com.example.name.mygame.game

sealed class Transition {
    class End(val finalScore: Int): Transition()
    object Quit: Transition()
}