package com.example.name.mygame.game.viewmodel

import android.app.Activity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.example.name.mygame.R
import org.jetbrains.anko.find

class InputProcessor(activity: Activity, private val dispatcher: Actions): View.OnTouchListener, View.OnClickListener {

    init {
        activity.find<Button>(R.id.resume).setOnClickListener(this)
        activity.find<Button>(R.id.quit).setOnClickListener(this)
        activity.find<View>(R.id.main_view).setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> dispatcher.selectPiece(event.x, event.y)
            MotionEvent.ACTION_UP -> dispatcher.endSelectingPieces()
        }
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.resume -> dispatcher.resume()
            R.id.quit -> dispatcher.quit()
        }
    }
}