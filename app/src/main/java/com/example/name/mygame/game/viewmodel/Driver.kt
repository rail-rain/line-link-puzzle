package com.example.name.mygame.game.viewmodel

import com.example.name.mygame.game.model.GameSystem
import com.example.name.mygame.game.model.TypeOfPiece
import com.example.name.mygame.game.util.GameActivity

class Driver(gameActivity: GameActivity, val highScore: Int): GameSystem(gameActivity), Actions, State {
    private val scale = gameActivity.resources.displayMetrics.density

    override fun selectPiece(selectedX: Float, selectedY: Float) {
        super.selectPiece(selectedX / scale / 48, selectedY / scale / 48)
    }

    override fun endSelectingPieces() {
        super.endSelectingPieces()
    }

    override fun getScore() = score.current.toString()
    override fun getHighScore() = highScore.toString()
    override fun getCount() = counter.count.toString()

    override fun getPieces() = gameSpace.pieces.map {
        Piece(it.position.x * scale * 48, it.position.y * scale * 48, it.userData as TypeOfPiece)
    }

    override fun getSelectedPieces() = gameSpace.selectedPieces.map {
        Piece(it.position.x * scale * 48, it.position.y * scale * 48, it.userData as TypeOfPiece)
    }
}