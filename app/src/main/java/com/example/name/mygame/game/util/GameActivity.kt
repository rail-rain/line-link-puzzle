package com.example.name.mygame.game.util

import android.support.v7.app.AppCompatActivity
import android.view.View
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

abstract class GameActivity(fps: Long): AppCompatActivity() {
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var future: ScheduledFuture<*>? = null
    private val period = 1000 / fps

    private lateinit var view: View

    protected abstract fun update()

    override fun setContentView(view: View) {
        super.setContentView(view)
        this.view = view
    }

    override fun onResume() {
        super.onResume()
        future = executor.scheduleAtFixedRate({
            update()
            view.postInvalidate()
        }, 0, period, TimeUnit.MILLISECONDS)
    }

    override fun onPause() {
        super.onPause()
        future!!.cancel(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }
}