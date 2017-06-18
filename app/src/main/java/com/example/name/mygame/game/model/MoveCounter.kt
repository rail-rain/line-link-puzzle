package com.example.name.mygame.game.model

class MoveCounter {
    var count: Int = 10
        private set
    fun move() {
        count--
    }
    fun isEnd() = count == 0
}