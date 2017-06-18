package com.example.name.mygame.game.model

import com.example.name.mygame.game.Transition
import com.example.name.mygame.game.util.System

class GameSystem: System<Transition> {
    private val score = Score()
    private val gameSpace = GameSpace()
    private val counter = MoveCounter()

    override var transition: Transition? = null

    fun selectPiece(selectedX: Float, selectedY: Float) {
        gameSpace.selectPiece(selectedX, selectedY)
    }

    fun endSelectingPieces() {
        val size = gameSpace.selectedPieces.size
        if (gameSpace.endSelectingPieces()) {
            score.increaseScore(size)
            counter.move()
            if (counter.isEnd()) transition = Transition.Quit
        }
    }

    override fun update() {
        gameSpace.step()
    }

    fun getScore() = score.current
    fun getPieces() = gameSpace.pieces
    fun getSelectedPieces() = gameSpace.selectedPieces
}