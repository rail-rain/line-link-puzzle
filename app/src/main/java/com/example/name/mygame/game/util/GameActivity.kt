package com.example.name.mygame.game.util

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

abstract class GameActivity(fps: Long): AppCompatActivity() {
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var future: ScheduledFuture<*>? = null
    private val period = 1000 / fps

    protected abstract val mainView: View
    protected abstract val pauseMenu: View
    protected abstract val system: System

    abstract fun<T> end(result: T)

    open fun quit() {
        finish()
    }

    fun update() {
        system.update()
        mainView.postInvalidate()
    }

    fun resume() {
        future = executor.scheduleAtFixedRate(this::update, 0, period, TimeUnit.MILLISECONDS)
        pauseMenu.visibility = View.INVISIBLE
    }

    fun pause() {
        future!!.cancel(true)
        pauseMenu.visibility = View.VISIBLE
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        resume()
    }

    override fun onPause() {
        super.onPause()
        if (pauseMenu.visibility == View.INVISIBLE) pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            pause()
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}