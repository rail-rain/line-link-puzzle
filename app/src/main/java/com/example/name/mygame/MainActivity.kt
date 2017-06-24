package com.example.name.mygame

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.example.name.mygame.game.Game
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            button("start") {
                setOnClickListener {
                    startActivityForResult<Game>(requestCode = 0, params =
                    Game.HIGH_SCORE to PreferenceManager.getDefaultSharedPreferences(this@MainActivity).getInt(Game.HIGH_SCORE, 0))
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val score = data!!.getIntExtra(Game.SCORE, 0)
            saveHighScore(score)
        }
    }

    private fun saveHighScore(score: Int) {
        val db = PreferenceManager.getDefaultSharedPreferences(this)
        if (score > db.getInt(Game.HIGH_SCORE, 0)) {
            db.edit().putInt(Game.HIGH_SCORE, score).apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
