package com.example.name.mygame.game

import android.content.Context
import android.view.View
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GameModule(val context: Context) {
    @Singleton @Provides
    fun provideActions(viewModel: ViewModel): Actions = viewModel

    @Singleton @Provides
    fun provideContext(): Context = context

    @Singleton @Provides
    fun provideOnTouchListener(ip: InputProcessor): View.OnTouchListener = ip

    @Singleton @Provides
    fun provideScore(): Score = ScoreImpl()

    @Singleton @Provides
    fun provideGameSpace(): GameSpace = GameSpaceImpl()

    @Singleton @Provides
    fun provideLifeCycleObserver(gameLoop: GameLoop): LifeCycleObserver = gameLoop
}