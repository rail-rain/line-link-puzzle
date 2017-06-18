package com.example.name.mygame

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.name.mygame.game.Game
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button("start") {
                setOnClickListener {
                    startActivityForResult<Game>(requestCode = 0)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val score = data!!.getIntExtra(Game.SCORE, 0)
            println(score)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
