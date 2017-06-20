package com.example.name.mygame.game.ui

import android.view.View
import com.example.name.mygame.R
import com.example.name.mygame.game.Game
import org.jetbrains.anko.*

class GameView: AnkoComponent<Game> {

    override fun createView(ui: AnkoContext<Game>) = with(ui) {
        frameLayout {
            val mainView = MainView(ctx, owner.system)
            mainView.id = R.id.main_view
            addView(mainView)

            verticalLayout {
                id = R.id.pause_menu
                visibility = View.INVISIBLE

                button("resume") {
                    id = R.id.resume
                }
                button("quit") {
                    id = R.id.quit
                }
            }
        }
    }
}