package com.example.name.mygame.game

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class GameActivity : AppCompatActivity() {

    @Inject lateinit var view: View
    @Inject lateinit var viewModel: ViewModel
    @Inject lateinit var lifecycleobserver: LifeCycleObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationComponent = DaggerGameComponent.builder()
                .gameModule(GameModule(this))
                .build()
        applicationComponent.inject(this)
        viewModel.addObserver({ o, arg -> view.postInvalidate() })
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        lifecycleobserver.resume()
    }

    override fun onPause() {
        super.onPause()
        lifecycleobserver.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleobserver.destroy()
    }
}
