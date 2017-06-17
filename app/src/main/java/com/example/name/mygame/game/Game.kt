package com.example.name.mygame.game

import android.os.Bundle
import com.example.name.mygame.game.util.GameActivity
import com.example.name.mygame.game.ui.View
import com.example.name.mygame.game.ui.InputProcessor

class Game : GameActivity(fps = 30) {

    lateinit var driver: Driver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        driver = Driver(this)
        val view = View(this, driver)
        val inputProcessor = InputProcessor(driver)
        view.setOnTouchListener(inputProcessor)
        setContentView(view)
    }

    override fun update() {
        driver.update()
    }
}
