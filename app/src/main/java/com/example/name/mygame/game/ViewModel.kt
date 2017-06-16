package com.example.name.mygame.game

import android.content.Context
import java.util.*
import javax.inject.Inject

class ViewModel @Inject constructor(val gameSpace: GameSpace, val score: Score, context: Context): Actions, Observable() {
    val scale = context.resources.displayMetrics.density
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
        notifyObservers()
    }
}