package com.example.name.mygame.game.view

import android.view.View
import com.example.name.mygame.R
import com.example.name.mygame.game.Game
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.listeners.onClick

class GameUI : AnkoComponent<Game> {

    lateinit var mainView: View
    lateinit var pauseView: View

    override fun createView(ui: AnkoContext<Game>) = with(ui) {
        frameLayout {
            mainView = MainView(ctx, owner.system)
            addView(mainView)

            pauseView = verticalLayout {
                visibility = View.INVISIBLE

                button("resume") {
                    onClick { owner.system.resume() }
                }
                button("quit") {
                    onClick { owner.system.quit() }
                }
            }
        }
    }
}