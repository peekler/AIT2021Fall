package hu.ait.tictactoe.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    lateinit var paintBackGround: Paint
    lateinit var paintLine: Paint

    private var circles = mutableListOf<PointF>()

    //var cX = -1f
    //var cY = -1f


    init {
        paintBackGround = Paint()
        paintBackGround.color = Color.BLACK
        paintBackGround.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackGround)
        canvas?.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        //if (cX != -1f && cY != -1f) {
        //    canvas?.drawCircle(cX, cY, 50f, paintLine)
        //}

        circles.forEach {
            canvas?.drawCircle(it.x, it.y, 50f, paintLine)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            //cX = event.x
            //cY = event.y
            circles.add(PointF(event.x, event.y))

            invalidate() // redraw - calls the draw() method eventually
        }

        return true
    }


    public fun resetGame() {
        circles.clear()
        invalidate()
    }

}