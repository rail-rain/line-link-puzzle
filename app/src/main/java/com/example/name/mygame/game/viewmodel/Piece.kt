package com.example.name.mygame.game.viewmodel

import com.example.name.mygame.game.model.TypeOfPiece

data class Piece(
        val positionX: Float,
        val positionY: Float,
        val type: TypeOfPiece
)