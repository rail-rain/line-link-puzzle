package com.example.name.mygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.name.mygame.game.GameActivity
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button("start") {
                setOnClickListener {
                    val intent = Intent(application, GameActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
