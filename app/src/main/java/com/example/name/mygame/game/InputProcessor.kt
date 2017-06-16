package com.example.name.mygame.game

import android.view.MotionEvent
import android.view.View
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InputProcessor @Inject constructor(private val dispatcher: Actions): View.OnTouchListener {
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> dispatcher.selectPiece(event.x, event.y)
            MotionEvent.ACTION_UP -> dispatcher.endSelectingPieces()
        }
        return true
    }
}