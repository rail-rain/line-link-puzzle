package com.example.name.mygame.game

interface Score {
    var current: Int
    fun increaseScore(deletedPieces: Int)
}

class ScoreImpl: Score {
    override var current: Int = 0

    override fun increaseScore(deletedPieces: Int) {
        current += deletedPieces * deletedPieces
    }
}