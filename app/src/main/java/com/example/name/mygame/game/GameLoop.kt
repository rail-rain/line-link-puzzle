package com.example.name.mygame.game

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GameLoop @Inject constructor(private val dispatcher: Actions): LifeCycleObserver {
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var future: ScheduledFuture<*>? = null

    override fun resume() {
        future = executor.scheduleAtFixedRate({ dispatcher.update() }, 0, 33, TimeUnit.MILLISECONDS)
    }

    override fun pause() {
        future!!.cancel(true)
    }

    override fun destroy() {
        executor.shutdown()
    }
}