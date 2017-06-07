package com.mygdx.game.game

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.World
import ktx.inject.Context
import ktx.box2d.*

class Model(context: Context) {
    private val MAX_BALL = 45
    private val PIECE_SIZE = 2
    private val PIECE_RADIUS = 1f

    val camera by lazy { context.inject<OrthographicCamera>() }
    val world by lazy { context.inject<World>() }
    val selectedPieces by lazy { context.inject<State>().selectedPieces }
    private val score by lazy { context.inject<State>().score }

    fun createPiece(context: Context) {
        val state = context.inject<State>()
        for (index in 0..MAX_BALL) {
            val piece = world.body {
                type = DynamicBody
                position.set(camera.viewportWidth / 2, camera.viewportHeight + index * PIECE_SIZE)
                circle(radius = PIECE_RADIUS) {
                    density = 1.0f
                    restitution = 0.6f
                    friction = 0.3f
                }
            }
            piece.userData = TypeOfPiece.choose()
            state.pieces.add(piece)
        }
    }

    fun createGround() {
        world.body {
            chain(
                    Vector2(0f, camera.viewportHeight),
                    Vector2(0f, camera.viewportHeight / 10),
                    Vector2(camera.viewportWidth / 2, 0f),
                    Vector2(camera.viewportWidth, camera.viewportHeight / 10),
                    Vector2(camera.viewportWidth, camera.viewportHeight)
            ) {}
        }
    }

    fun selectPiece(selectedPoint: Vector3) {
        world.QueryAABB(object: QueryCallback {
            override fun reportFixture(pieceFixture: Fixture): Boolean {
                if (!pieceFixture.testPoint(selectedPoint.x, selectedPoint.y)) return true
                val piece = pieceFixture.getBody()

                if (selectedPieces.contains(piece)) {
                    if (selectedPieces.peek() != piece) {
                        selectedPieces.pop()
                    }
                    return false
                }
                if (selectedPieces.size != 0
                        && (piece.getUserData() != selectedPieces[0].getUserData()
                        || piece.position.dst(selectedPieces.peek().position) > 3)) {
                    return false
                }

                selectedPieces.add(piece)
                return false
            }
        }, selectedPoint.x, selectedPoint.y, selectedPoint.x, selectedPoint.y)
    }

    fun endSelectingPieces() {
        score.increaseScore(selectedPieces.size)
        if (selectedPieces.size >= 3) {
            for ((index, body) in selectedPieces.withIndex()) {
                body.userData = TypeOfPiece.choose()
                body.setTransform(camera.viewportWidth / 2, camera.viewportHeight + index * PIECE_SIZE, 0f)
            }
        }
        selectedPieces.clear()
    }
}