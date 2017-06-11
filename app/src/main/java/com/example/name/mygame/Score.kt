package com.example.name.mygame

object Score{
    var current: Int = 0

    fun increaseScore(deletedPieces: Int) {
        current += deletedPieces * deletedPieces
    }
}