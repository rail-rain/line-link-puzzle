package com.mygdx.game.game

import java.util.*

enum class TypeOfPiece {
    ONE, TWO, THREE, FOUR, FIVE;

    companion object {
        val rand = java.util.Random()
        fun choose() = enumValues<TypeOfPiece>()[TypeOfPiece.Companion.rand.nextInt(5)]
    }
}