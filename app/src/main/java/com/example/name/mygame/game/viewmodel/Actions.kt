package com.example.name.mygame.game.viewmodel

interface Actions {
    fun selectPiece(selectedX: Float, selectedY: Float)
    fun endSelectingPieces()
}