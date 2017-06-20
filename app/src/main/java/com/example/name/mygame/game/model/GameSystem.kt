package com.example.name.mygame.game.model

import com.example.name.mygame.game.util.GameActivity
import com.example.name.mygame.game.util.System

open class GameSystem(gameActivity: GameActivity): System(gameActivity) {
    protected val score = Score()
    protected val gameSpace = GameSpace()
    protected val counter = MoveCounter()

    open fun selectPiece(selectedX: Float, selectedY: Float) {
        gameSpace.selectPiece(selectedX, selectedY)
    }

    open fun endSelectingPieces() {
        val size = gameSpace.selectedPieces.size
        if (gameSpace.endSelectingPieces()) {
            score.increaseScore(size)
            counter.move()
            if (counter.isEnd()) end(score.current)
        }
    }

    override fun update() {
        gameSpace.step()
    }
}