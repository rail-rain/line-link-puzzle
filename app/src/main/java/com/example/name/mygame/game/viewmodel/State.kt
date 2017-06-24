package com.example.name.mygame.game.viewmodel

interface State {
    fun getHighScore(): String
    fun getScore(): String
    fun getCount(): String
    fun getPieces(): Collection<Piece>
    fun getSelectedPieces(): Collection<Piece>
}