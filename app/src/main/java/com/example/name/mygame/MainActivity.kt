package com.example.name.mygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = View(this)
        view.setOnTouchListener(Reducer)
        setContentView(view)

        val handler = object: Handler() {
            override fun handleMessage(msg: Message) {
                view.invalidate()
            }
        }
        timer(period = 33) {
            Reducer.update()
            handler.sendMessage(Message.obtain())
        }
    }
}
