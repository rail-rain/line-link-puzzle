package com.mygdx.game

import java.util.*

enum class TypeOfPiece {
    ONE, TWO, THREE, FOUR, FIVE;

    companion object {
        val rand = Random()
        fun choose() = enumValues<TypeOfPiece>()[rand.nextInt(5)]
    }
}