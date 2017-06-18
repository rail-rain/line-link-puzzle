package com.example.name.mygame.game

import android.os.Bundle
import com.example.name.mygame.game.model.GameSystem
import com.example.name.mygame.game.util.GameActivity
import com.example.name.mygame.game.ui.View
import com.example.name.mygame.game.ui.InputProcessor

class Game : GameActivity<Transition>(fps = 30) {
    override val system: GameSystem = GameSystem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val driver = Driver(this, system)
        val view = View(this, driver)
        val inputProcessor = InputProcessor(driver)
        view.setOnTouchListener(inputProcessor)
        setContentView(view)
    }

    override fun transit(transition: Transition) {
        when(transition) {
            Transition.Quit -> finish()
        }
    }
}
