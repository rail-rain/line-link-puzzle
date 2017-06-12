package com.example.name.mygame.game

import android.view.MotionEvent
import android.view.View

object Reducer: View.OnTouchListener {
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val scale = v.resources.displayMetrics.density / 48
        when (event.action) {
            MotionEvent.ACTION_MOVE -> GameSpace.selectPiece(event.x / scale, event.y / scale)
            MotionEvent.ACTION_UP -> endSelectingPieces()
        }
        return true
    }

    fun endSelectingPieces() {
        val size = GameSpace.selectedPieces.size
        if (GameSpace.endSelectingPieces()) {
        Score.increaseScore(size)
        }
    }

    fun update() {
        GameSpace.step()
    }
}