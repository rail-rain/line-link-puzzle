package com.example.name.mygame.game.util

interface System<T> {
    var transition: T?

    fun update()
}