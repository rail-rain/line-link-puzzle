package com.example.name.mygame.game

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.name.mygame.game.view.GameUI
import com.example.name.mygame.game.util.GameActivity
import com.example.name.mygame.game.viewmodel.InputProcessor
import com.example.name.mygame.game.viewmodel.Driver
import org.jetbrains.anko.setContentView

class Game : GameActivity(fps = 30) {
    companion object {
        const val SCORE = "s"
    }
    override public val system by lazy { Driver(this) }
    override val mainView by lazy { ui.mainView }
    override val pauseMenu by lazy { ui.pauseView }

    private val ui = GameUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        InputProcessor(this, system)
    }

    override fun<T> end(result: T) {
        val intent = Intent()
        intent.putExtra(SCORE, result as Int)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
