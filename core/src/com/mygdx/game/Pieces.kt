package com.mygdx.game

import java.util.Random

import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.QueryCallback

enum class PieceType {
  ONE, TWO, THREE, FOUR;

  companion object {
    val rand = Random()
    fun random() = enumValues<PieceType>()[rand.nextInt(4)]
  }
}

class Pieces(val camera: OrthographicCamera, val world: World) {
  private val MAX_BALL = 30
  private val PIECE_RADIUS = 1f
  private val PIECE_SIZE = 2

  var bodies: ArrayList<Body> = ArrayList()
  var selectedPieces: Array<Body> = Array()

  init {
  	val bodyDef = BodyDef()
  	bodyDef.type = BodyType.DynamicBody
  	val circle = CircleShape()
  	circle.setRadius(PIECE_RADIUS)
  	val fixtureDef = FixtureDef()
  	fixtureDef.shape = circle
  	fixtureDef.density = 1.0f
  	fixtureDef.restitution = 0.6f
  	fixtureDef.friction = 0.3f

  	val width = camera.viewportWidth / 2
  	for (index in 0..MAX_BALL) {
  		val body = world.createBody(bodyDef)
      body.setUserData(PieceType.random())
  		body.setTransform(width, camera.viewportHeight + index * PIECE_SIZE, 0f)
  		body.createFixture(fixtureDef)
      bodies.add(body)
  	}
  	circle.dispose()
  }

  fun selectPiece(selectPoint: Vector3) {
    world.QueryAABB(object: QueryCallback {
      override fun reportFixture(pieceFixture: Fixture): Boolean {
        if (!pieceFixture.testPoint(selectPoint.x, selectPoint.y)) return true
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
    }, selectPoint.x, selectPoint.y, selectPoint.x, selectPoint.y)
  }

  fun endSelectingPieces() {
    if (selectedPieces.size >= 3) {
      for ((index, body) in selectedPieces.withIndex()) {
        body.setUserData(PieceType.random())
        body.setTransform(camera.viewportWidth / 2, camera.viewportHeight + index * PIECE_SIZE, 0f)
      }
    }
    selectedPieces.clear()
  }
}
