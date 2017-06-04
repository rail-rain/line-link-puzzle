package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.physics.box2d.Box2D
import com.badlogic.gdx.physics.box2d.World
import ktx.inject.Context

class MyGdxGame : ApplicationAdapter() {
	private lateinit var camera: OrthographicCamera
	private lateinit var world: World
	private lateinit var state: State
	private lateinit var context: Context

	override fun create() {
		camera = OrthographicCamera(11.5f, 20.5f)
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f)

		Box2D.init()
		world = World(Vector2(0f, -9.81f), false)

		context = Context()

        state = State()
        val model = Model(context)
        val intent = Intent(context)

        context.register {
            bindSingleton(camera)
            bindSingleton(world)
            bindSingleton(state)
            bindSingleton(model)
        }

		Gdx.input.inputProcessor = intent

		model.createGround()
        model.createPiece(context)
	}

	override fun render() {
		camera.update()
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		val renderer = ShapeRenderer(500)
		renderer.setProjectionMatrix(camera.combined)
		renderer.begin(ShapeType.Line)
		for (body in state.pieces) {
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
		for (body in state.selectedPieces) {
			val pos = body.getPosition()
			if (prevPos != null) {
				renderer.line(prevPos.x, prevPos.y, pos.x, pos.y)
			}
			prevPos = pos
		}
		renderer.end()
		world.step(1/60f, 6, 2)
	}

	override fun dispose() {
	}
}
