package com.example.name.mygame.game

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity() {

    private val game_loop = Executors.newSingleThreadScheduledExecutor()
    private var game_loop_future: ScheduledFuture<*>? = null
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = View(this)
        view.setOnTouchListener(Reducer)
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        game_loop_future = game_loop.scheduleAtFixedRate({
            Reducer.update()
            view.postInvalidate()
        }, 0, 33, TimeUnit.MILLISECONDS)
    }

    override fun onPause() {
        super.onPause()
        game_loop_future!!.cancel(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        game_loop.shutdown()
    }
}
