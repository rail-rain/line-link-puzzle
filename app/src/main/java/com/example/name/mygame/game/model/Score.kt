package com.example.name.mygame.game.model

class Score {
    var current: Int = 0

    fun increaseScore(deletedPieces: Int) {
        current += deletedPieces * deletedPieces
    }
}