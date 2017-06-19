package com.example.name.mygame.game

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.name.mygame.game.model.GameSystem
import com.example.name.mygame.game.util.GameActivity
import com.example.name.mygame.game.ui.MainView
import com.example.name.mygame.game.ui.InputProcessor
import com.example.name.mygame.game.viewmodel.Driver

class Game : GameActivity<Transition>(fps = 30) {
    companion object {
        const val SCORE = "s"
    }
    override val system: GameSystem = GameSystem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val driver = Driver(this, system)
        mainView = MainView(this, driver)
        val inputProcessor = InputProcessor(driver)
        mainView.setOnTouchListener(inputProcessor)
    }

    override fun transit(transition: Transition) {
        when(transition) {
            is Transition.End -> {
                val intent = Intent()
                intent.putExtra(SCORE, transition.finalScore)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            Transition.Quit -> finish()
        }
    }
}
