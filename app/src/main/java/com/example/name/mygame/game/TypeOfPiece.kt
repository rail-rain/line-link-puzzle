package com.example.name.mygame.game

enum class TypeOfPiece {
    ONE, TWO, THREE, FOUR, FIVE;

    companion object {
        val rand = java.util.Random()
        fun pickupNext() = enumValues<TypeOfPiece>()[TypeOfPiece.Companion.rand.nextInt(5)]
    }
}