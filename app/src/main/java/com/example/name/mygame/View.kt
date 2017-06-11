package com.example.name.mygame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View

class View(context: Context): View(context) {

    val paint = Paint()

    init {
        paint.strokeWidth = 10f
        paint.textSize = 100f
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
            canvas.drawCircle(body.position.x * 114, body.position.y * 158.4f, canvas.height / 20f, paint)
        }
        paint.color = Color.rgb(255, 255, 255)
        paint.style = Paint.Style.STROKE
        val path = Path()
        for ((index, body) in GameSpace.selectedPieces.withIndex()) {
            if (index == 0) {
                path.moveTo(body.position.x * 114, body.position.y * 158.4f)
            } else {
                path.lineTo(body.position.x * 114, body.position.y * 158.4f)
            }
        }
        canvas.drawPath(path, paint)
        canvas.drawText(Score.current.toString(), 100f, 100f, paint)
    }
}