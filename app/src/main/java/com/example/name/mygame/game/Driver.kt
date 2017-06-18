package com.example.name.mygame.game

import android.content.Context
import com.example.name.mygame.game.model.GameSystem
import com.example.name.mygame.game.model.TypeOfPiece
import com.example.name.mygame.game.ui.Piece
import com.example.name.mygame.game.ui.State
import com.example.name.mygame.game.ui.Actions

class Driver(context: Context, val system: GameSystem): Actions, State {
    private val scale = context.resources.displayMetrics.density

    override fun selectPiece(selectedX: Float, selectedY: Float) {
        system.selectPiece(selectedX / scale / 48, selectedY / scale / 48)
    }

    override fun endSelectingPieces() {
        system.endSelectingPieces()
    }

    override fun getScore() = system.getScore().toString()
    override fun getCount() = system.getCount().toString()

    override fun getPieces() = system.getPieces().map {
        Piece(it.position.x * scale * 48, it.position.y * scale * 48, it.userData as TypeOfPiece)
    }

    override fun getSelectedPieces() = system.getSelectedPieces().map {
        Piece(it.position.x * scale * 48, it.position.y * scale * 48, it.userData as TypeOfPiece)
    }
}