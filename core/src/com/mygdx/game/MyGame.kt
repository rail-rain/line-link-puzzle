package com.mygdx.game

import java.util.ArrayList
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.physics.box2d.Box2D
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.ChainShape

class MyGdxGame : ApplicationAdapter(), InputProcessor {
	private lateinit var camera: OrthographicCamera
	private lateinit var world: World
	private lateinit var debugRenderer: Box2DDebugRenderer
	private lateinit var pieces: Pieces

	override fun create() {
		camera = OrthographicCamera(11.5f, 20.5f)
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f)

		Box2D.init()
		world = World(Vector2(0f, -9.81f), true)

		debugRenderer = Box2DDebugRenderer()

		Gdx.input.setInputProcessor(this)

		createGround()
		pieces = Pieces(camera, world)
	}

	override fun render() {
		camera.update()
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		val renderer = ShapeRenderer(500)
		renderer.setProjectionMatrix(camera.combined)
		renderer.begin(ShapeType.Line)
		for (body in pieces.bodies) {
			when (body.getUserData()) {
				PieceType.ONE -> renderer.setColor(1.0f, 0f, 0f, 0f)
				PieceType.TWO -> renderer.setColor(0f, 1.0f, 0f, 0f)
				PieceType.THREE -> renderer.setColor(0f, 0f, 1.0f, 0f)
				PieceType.FOUR -> renderer.setColor(1.0f, 1.0f, 0f, 0f)
			}
			val position = body.getPosition()
			renderer.circle(position.x, position.y, 1f)
		}
		renderer.setColor(1f, 1f, 1f, 1f)
		var prevPos: Vector2? = null
		for (body in pieces.selectedPieces) {
			val pos = body.getPosition()
			if (prevPos != null) {
				renderer.line(prevPos.x, prevPos.y, pos.x, pos.y)
			}
			prevPos = pos
		}
		renderer.end()
		/*debugRenderer.render(world, camera.combined)*/
		world.step(1/60f, 6, 2)
	}

	override fun dispose() {
	}

	private fun createGround() {
		val bodyDef = BodyDef()
		val body = world.createBody(bodyDef)
		val groundShape = ChainShape()
		groundShape.createChain(arrayOf(
			Vector2(0f, camera.viewportHeight),
			Vector2(0f, camera.viewportHeight / 10),
			Vector2(camera.viewportWidth / 2, 0f),
			Vector2(camera.viewportWidth, camera.viewportHeight / 10),
			Vector2(camera.viewportWidth, camera.viewportHeight)
			))
		body.createFixture(groundShape, 0f)
		groundShape.dispose()
	}

	private var testPoint = Vector3()

	override fun touchDown (screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if (button != Input.Buttons.LEFT || pointer > 0) return false
		camera.unproject(testPoint.set(screenX.toFloat(), screenY.toFloat(), 0f))
		return true
	}

	override fun touchDragged (screenX: Int, screenY: Int, pointer: Int): Boolean {
		camera.unproject(testPoint.set(screenX.toFloat(), screenY.toFloat(), 0f))
		pieces.selectPiece(testPoint)
		return true
	}

	override fun touchUp (screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if (button != Input.Buttons.LEFT || pointer > 0) return false
		pieces.endSelectingPieces()
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
