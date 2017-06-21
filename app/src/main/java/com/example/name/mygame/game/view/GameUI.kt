package com.example.name.mygame.game.view

import android.view.MotionEvent
import android.view.View
import android.view.ViewManager
import com.example.name.mygame.game.Game
import com.example.name.mygame.game.viewmodel.State
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.listeners.onClick
import org.jetbrains.anko.sdk25.listeners.onTouch

class GameUI : AnkoComponent<Game> {

    lateinit var mainView: View
    lateinit var pauseView: View

    override fun createView(ui: AnkoContext<Game>) = with(ui) {
        val dispatcher = owner.system
        frameLayout {
            mainView = mainView(owner.system) {
                onTouch { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_MOVE -> dispatcher.selectPiece(event.x, event.y)
                        MotionEvent.ACTION_UP -> dispatcher.endSelectingPieces()
                    }
                    true
                }
            }

            pauseView = verticalLayout {
                button("resume") {
                    onClick { dispatcher.resume() }
                }
                button("quit") {
                    onClick { dispatcher.quit() }
                }
            }
        }
    }

    inline fun ViewManager.mainView(state: State, init: MainView.() -> Unit)
            = ankoView({ MainView(it, state) }, 0, init)
}