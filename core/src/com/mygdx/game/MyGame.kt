package com.mygdx.game

import com.badlogic.gdx.Game
import com.mygdx.game.game.Screen

class MyGdxGame : Game() {
    override fun create() {
        setScreen(Screen())
    }

}
