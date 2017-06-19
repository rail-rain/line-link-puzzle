package com.example.name.mygame.game.viewmodel

interface State {
    fun getScore(): String
    fun getCount(): String
    fun getPieces(): Collection<Piece>
    fun getSelectedPieces(): Collection<Piece>
}