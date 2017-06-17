package com.example.name.mygame.game.ui

interface Actions {
    fun selectPiece(selectedX: Float, selectedY: Float)
    fun endSelectingPieces()
    fun update()
}