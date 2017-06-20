package com.example.name.mygame.game.util

import java.lang.ref.WeakReference

abstract class System(gameActivity: GameActivity) {
    val transmitter = WeakReference(gameActivity)

    abstract fun update()

    fun<T> end(result: T) {
        transmitter.get()?.end(result)
    }

    fun quit() {
        transmitter.get()?.quit()
    }

    fun resume() {
        transmitter.get()?.resume()
    }
}