package com.example.name.mygame

import android.view.MotionEvent
import android.view.View

object Reducer: View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> GameSpace.selectPiece(event.x / 114, event.y / 158.4f)
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