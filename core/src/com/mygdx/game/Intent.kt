package com.mygdx.game

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import ktx.inject.Context

class Intent(context: Context) : InputProcessor {
    private val camera by lazy { context.inject<OrthographicCamera>() }
    private val dispatcher by lazy { context.inject<Model>() }

    private var testPoint = Vector3()

    override fun touchDown (screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (button != Input.Buttons.LEFT || pointer > 0) return false
        camera.unproject(testPoint.set(screenX.toFloat(), screenY.toFloat(), 0f))
        return true
    }

    override fun touchDragged (screenX: Int, screenY: Int, pointer: Int): Boolean {
        camera.unproject(testPoint.set(screenX.toFloat(), screenY.toFloat(), 0f))

        dispatcher.selectPiece(testPoint)
        return true
    }

    override fun touchUp (screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (button != Input.Buttons.LEFT || pointer > 0) return false

        dispatcher.endSelectingPieces()
        return true
    }

    override fun mouseMoved (screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyDown (keycode: Int): Boolean {
        return false
    }

    override fun keyUp (keycode: Int): Boolean {
        return false
    }

    override fun keyTyped (character: Char): Boolean {
        return false
    }

    override fun scrolled (amount: Int): Boolean {
        return false
    }
}