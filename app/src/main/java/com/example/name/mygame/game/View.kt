package com.example.name.mygame.game

import android.content.Context
import android.graphics.*
import android.view.View
import com.example.name.mygame.R

class View(context: Context): View(context) {

    val paint = Paint()
    val scale = 48 * resources.displayMetrics.density
    val piece_radius = resources.getDimension(R.dimen.radius)
    val score_size = resources.getDimension(R.dimen.score)

    init {
        paint.strokeWidth = 10f
        paint.textAlign = Paint.Align.RIGHT
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(0, 0, 0)
        paint.style = Paint.Style.FILL
        for (body in GameSpace.pieces) {
            when (body.userData) {
                TypeOfPiece.ONE -> paint.color = Color.rgb(255, 0, 0)
                TypeOfPiece.TWO -> paint.color = Color.rgb(0, 255, 0)
                TypeOfPiece.THREE -> paint.color = Color.rgb(0, 0, 255)
                TypeOfPiece.FOUR -> paint.color = Color.rgb(255, 255, 0)
                TypeOfPiece.FIVE -> paint.color = Color.rgb(255, 0, 255)
            }
            canvas.drawCircle(body.position.x * scale, body.position.y * scale, piece_radius, paint)
        }
        paint.color = Color.rgb(255, 255, 255)
        paint.style = Paint.Style.STROKE
        val path = Path()
        for ((index, body) in GameSpace.selectedPieces.withIndex()) {
            if (index == 0) {
                path.moveTo(body.position.x * scale, body.position.y * scale)
            } else {
                path.lineTo(body.position.x * scale, body.position.y * scale)
            }
        }
        canvas.drawPath(path, paint)
        paint.textSize = score_size
        canvas.drawText(Score.current.toString(), canvas.width * 0.9f, 100f, paint)
    }
}