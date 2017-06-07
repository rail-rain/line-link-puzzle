package com.mygdx.game.game

class Score {
    var current: Int = 0

    fun increaseScore(deletedPieces: Int) {
        current += deletedPieces * deletedPieces
    }
}