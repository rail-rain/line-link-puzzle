package com.example.name.mygame.game.util

import java.lang.ref.WeakReference

abstract class System(gameActivity: GameActivity) {
    val transmitter = WeakReference(gameActivity)

    abstract fun update()

    fun<T> transEnd(result: T) {
        transmitter.get()?.end(result)
    }

    fun transQuit() {
        transmitter.get()?.quit()
    }

    fun transResume() {
        transmitter.get()?.resume()
    }
}