package com.example.name.mygame.game.model

enum class TypeOfPiece {
    ONE, TWO, THREE, FOUR, FIVE;

    companion object {
        val rand = java.util.Random()
        fun pickupNext() = enumValues<TypeOfPiece>()[rand.nextInt(5)]
    }
}