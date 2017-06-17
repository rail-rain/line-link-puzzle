package com.example.name.mygame.game.model

import org.jbox2d.collision.AABB
import org.jbox2d.collision.shapes.ChainShape
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.common.MathUtils
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.*
import java.util.*

private const val MAX_PIECE = 45
private const val PIECE_SIZE= 1f
private const val SPACE_WIDTH = 8.5f
private const val SPACE_HEIGHT = 10f

class GameSpace {

    private val world = World(Vec2(0f, 9.8f))
    val pieces: Array<Body>
    val selectedPieces = ArrayDeque<Body>()

    init {
        createGround()

        val (body, fixture) = createPieceDef()
        pieces = Array(MAX_PIECE, {index ->
            body.position = Vec2(SPACE_WIDTH / 2, -(SPACE_HEIGHT + index * PIECE_SIZE))
            val piece = world.createBody(body)
            piece.createFixture(fixture)
            piece.userData = TypeOfPiece.pickupNext()
            piece
        })
    }

    fun selectPiece(selectedX: Float, selectedY: Float) {
        val selectedPoint = Vec2(selectedX, selectedY)
        world.queryAABB(fun(pieceFixture): Boolean {
            if (!pieceFixture.testPoint(selectedPoint)) return true
            val piece = pieceFixture.body

            if (selectedPieces.contains(piece)) {
                if (selectedPieces.first() != piece) {
                    selectedPieces.pop()
                }
                return false
            }
            if (selectedPieces.size != 0 &&
                    (piece.userData != selectedPieces.last.userData
                    || MathUtils.distance(piece.position, selectedPieces.first().position) > 3)) {
                return false
            }

            selectedPieces.push(piece)
            return false
        }, AABB(selectedPoint, selectedPoint))
    }

    fun endSelectingPieces(): Boolean {
        val isDeleted = if (selectedPieces.size >= 3) {
            for ((index, body) in selectedPieces.withIndex()) {
                body.userData = TypeOfPiece.pickupNext()
                body.setTransform(Vec2(SPACE_WIDTH / 2, -(SPACE_HEIGHT + index * PIECE_SIZE)), 0f)
            }
            true
        } else {
            false
        }
        selectedPieces.clear()
        return isDeleted
    }

    fun step() {
        world.step(1 / 30f, 6, 2)
    }

    fun createGround() {
        val shape = ChainShape()
        shape.createChain(arrayOf(
                Vec2(0f, 0f),
                Vec2(0f, SPACE_HEIGHT * 0.9f),
                Vec2(SPACE_WIDTH / 2, SPACE_HEIGHT),
                Vec2(SPACE_WIDTH, SPACE_HEIGHT * 0.9f),
                Vec2(SPACE_WIDTH, 0f)
        ), 5)

        val fixture = FixtureDef()
        fixture.shape = shape

        val body = BodyDef()
        body.type = BodyType.STATIC
        val body2 = world.createBody(body)
        body2.createFixture(fixture)
    }

    fun createPieceDef(): Pair<BodyDef, FixtureDef> {
        val shape = CircleShape()
        shape.radius = PIECE_SIZE / 2

        val fixture = FixtureDef()
        fixture.shape = shape
        fixture.density = 1.0f
        fixture.restitution = 0.6f
        fixture.friction = 0.3f

        val body = BodyDef()
        body.type = BodyType.DYNAMIC
        return Pair(body, fixture)
    }
}
