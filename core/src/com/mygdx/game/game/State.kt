package com.mygdx.game.game

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.utils.Array

data class State(
        val pieces: ArrayList<Body> = ArrayList(),
        val selectedPieces: Array<Body> = Array(),
        val score: Score = Score()
)