package com.example.name.mygame.game

object Score{
    var current: Int = 0

    fun increaseScore(deletedPieces: Int) {
        current += deletedPieces * deletedPieces
    }
}