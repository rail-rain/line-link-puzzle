package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import ktx.inject.Context

class View(context: Context) {
    val camera by lazy { context.inject<OrthographicCamera>() }
    val pieces by lazy { context.inject<State>().pieces }
    val selectedPieces by lazy { context.inject<State>().selectedPieces }
    private val score by lazy { context.inject<State>().score }

    val spriteBatch = SpriteBatch()
    val font = BitmapFont()

    fun renderer() {
        camera.update()
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        val renderer = ShapeRenderer(500)
        renderer.setProjectionMatrix(camera.combined)
        renderer.begin(ShapeRenderer.ShapeType.Line)
        for (body in pieces) {
            when (body.userData) {
                TypeOfPiece.ONE -> renderer.setColor(1.0f, 0f, 0f, 0f)
                TypeOfPiece.TWO -> renderer.setColor(0f, 1.0f, 0f, 0f)
                TypeOfPiece.THREE -> renderer.setColor(0f, 0f, 1.0f, 0f)
                TypeOfPiece.FOUR -> renderer.setColor(1.0f, 1.0f, 0f, 0f)
                TypeOfPiece.FIVE -> renderer.setColor(1.0f, 0.0f, 1.0f, 0f)
            }
            val position = body.getPosition()
            renderer.circle(position.x, position.y, 1f)
        }
        renderer.setColor(1f, 1f, 1f, 1f)
        var prevPos: Vector2? = null
        for (body in selectedPieces) {
            val pos = body.getPosition()
            if (prevPos != null) {
                renderer.line(prevPos.x, prevPos.y, pos.x, pos.y)
            }
            prevPos = pos
        }
        renderer.end()
        spriteBatch.begin()
        font.draw(spriteBatch, score.current.toString(), 100f, 100f)
        spriteBatch.end()
    }
}