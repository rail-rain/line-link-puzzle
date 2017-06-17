package com.example.name.mygame.game.ui

import android.content.Context
import android.graphics.*
import android.view.View
import com.example.name.mygame.R
import com.example.name.mygame.game.model.TypeOfPiece

class View(context: Context, val state: State): View(context) {

    val paint = Paint()
    val piece_radius = resources.getDimension(R.dimen.radius)
    val score_size = resources.getDimension(R.dimen.score)

    init {
        paint.strokeWidth = 10f
        paint.textAlign = Paint.Align.RIGHT
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(0, 0, 0)
        paint.style = Paint.Style.FILL
        for (body in state.getPieces()) {
            when (body.type) {
                TypeOfPiece.ONE -> paint.color = Color.rgb(255, 0, 0)
                TypeOfPiece.TWO -> paint.color = Color.rgb(0, 255, 0)
                TypeOfPiece.THREE -> paint.color = Color.rgb(0, 0, 255)
                TypeOfPiece.FOUR -> paint.color = Color.rgb(255, 255, 0)
                TypeOfPiece.FIVE -> paint.color = Color.rgb(255, 0, 255)
            }
            canvas.drawCircle(body.positionX, body.positionY, piece_radius, paint)
        }
        paint.color = Color.rgb(255, 255, 255)
        paint.style = Paint.Style.STROKE
        val path = Path()
        for ((index, body) in state.getSelectedPieces().withIndex()) {
            if (index == 0) {
                path.moveTo(body.positionX, body.positionY)
            } else {
                path.lineTo(body.positionX, body.positionY)
            }
        }
        canvas.drawPath(path, paint)
        paint.textSize = score_size
        canvas.drawText(state.getScore(), canvas.width * 0.9f, 100f, paint)
    }
}