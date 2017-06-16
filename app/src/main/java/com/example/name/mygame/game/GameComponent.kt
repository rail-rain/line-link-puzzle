package com.example.name.mygame.game

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(GameModule::class))
interface GameComponent {
    fun inject(gameActivity: GameActivity)
}