package com.example.name.mygame.game

interface Actions {
    fun selectPiece(selectedX: Float, selectedY: Float)
    fun endSelectingPieces()
    fun update()
}