package com.example.name.mygame.game.view

import android.view.View
import com.example.name.mygame.R
import com.example.name.mygame.game.Game
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.listeners.onClick

class GameUI : AnkoComponent<Game> {

    override fun createView(ui: AnkoContext<Game>) = with(ui) {
        frameLayout {
            val mainView = MainView(ctx, owner.system)
            mainView.id = R.id.main_view
            addView(mainView)

            verticalLayout {
                id = R.id.pause_menu
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