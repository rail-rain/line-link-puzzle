package com.example.name.mygame.game.view

import android.view.MotionEvent
import android.view.View
import com.example.name.mygame.R
import com.example.name.mygame.game.Game
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.listeners.onClick

class GameUI : AnkoComponent<Game> {

    lateinit var mainView: View
    lateinit var pauseView: View

    override fun createView(ui: AnkoContext<Game>) = with(ui) {
        val dispatcher = owner.system
        frameLayout {
            mainView = MainView(ctx, owner.system)
            mainView.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_MOVE -> dispatcher.selectPiece(event.x, event.y)
                    MotionEvent.ACTION_UP -> dispatcher.endSelectingPieces()
                }
                true
            }
            addView(mainView)

            pauseView = verticalLayout {
                visibility = View.INVISIBLE

                button("resume") {
                    onClick { dispatcher.resume() }
                }
                button("quit") {
                    onClick { dispatcher.quit() }
                }
            }
        }
    }
}