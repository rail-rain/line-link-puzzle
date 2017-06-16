package com.example.name.mygame.game

interface LifeCycleObserver {
    fun resume()
    fun pause()
    fun destroy()
}