package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.physics.box2d.Box2D
import com.badlogic.gdx.physics.box2d.World
import ktx.inject.Context

class MyGdxGame : ApplicationAdapter() {
	private lateinit var camera: OrthographicCamera
	private lateinit var world: World
	private lateinit var state: State
	private lateinit var context: Context
    private lateinit var view: View

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

		view = View(context)
	}

	override fun render() {
        view.renderer()
		world.step(1/60f, 6, 2)
	}

	override fun dispose() {
	}
}
