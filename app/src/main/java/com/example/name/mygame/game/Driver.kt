package com.example.name.mygame.game

import android.content.Context
import com.example.name.mygame.game.model.GameSpace
import com.example.name.mygame.game.model.Score
import com.example.name.mygame.game.model.TypeOfPiece
import com.example.name.mygame.game.ui.Piece
import com.example.name.mygame.game.ui.State
import com.example.name.mygame.game.ui.Actions

class Driver(context: Context): Actions, State {

    private val scale = context.resources.displayMetrics.density
    private val score = Score()
    private val gameSpace = GameSpace()

    override fun selectPiece(selectedX: Float, selectedY: Float) {
        gameSpace.selectPiece(selectedX / scale / 48, selectedY / scale / 48)
    }

    override fun endSelectingPieces() {
        val size = gameSpace.selectedPieces.size
        if (gameSpace.endSelectingPieces()) {
            score.increaseScore(size)
        }
    }

    override fun update() {
        gameSpace.step()
    }

    override fun getScore() = score.current.toString()

    override fun getPieces() = gameSpace.pieces.map {
        Piece(it.position.x * scale * 48, it.position.y * scale * 48, it.userData as TypeOfPiece)
    }

    override fun getSelectedPieces() = gameSpace.selectedPieces.map {
        Piece(it.position.x * scale * 48, it.position.y * scale * 48, it.userData as TypeOfPiece)
    }
}