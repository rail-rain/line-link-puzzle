package com.example.name.mygame.game.util

import android.support.v7.app.AppCompatActivity
import android.view.View
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

abstract class GameActivity<T>(fps: Long): AppCompatActivity() {
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var future: ScheduledFuture<*>? = null
    private val period = 1000 / fps

    protected lateinit var mainView: View
    protected abstract val system: System<T>

    protected abstract fun transit(transition: T)

    fun update() {
        if (system.transition != null) {
            transit(system.transition as T)
        }
        system.update()
        mainView.postInvalidate()
    }

    fun resume() {
        future = executor.scheduleAtFixedRate({ this.update() }, 0, period, TimeUnit.MILLISECONDS)
    }

    fun pause() {
        future!!.cancel(true)
    }

    override fun onResume() {
        super.onResume()
        resume()
    }

    override fun onPause() {
        super.onPause()
        pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }
}