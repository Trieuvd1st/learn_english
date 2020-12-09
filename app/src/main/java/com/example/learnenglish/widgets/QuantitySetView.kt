package com.example.learnenglish.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.learnenglish.R


class QuantitySetView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
        View(context, attrs, defStyle) {
    var paint = Paint()
    var bounds = Rect()
    var color: Int = Color.BLACK
    var widthSize: Float = 0f
    var heightSize: Float = 0f
    var max: Int = 5
    var value: Int = 0
    var textSize = 36f
    var buttonSize = 30f
    var paddingHorizontal = 20f

    init {
        val a =
                context.theme.obtainStyledAttributes(
                        attrs,
                        R.styleable.QuantitySetView,
                        0,
                        0
                )
        color = a.getColor(
                R.styleable.QuantitySetView_setColor, ContextCompat.getColor(context, R.color.black)
        )

        widthSize = a.getDimension(
                R.styleable.QuantitySetView_setWidth,
                resources.getDimension(R.dimen.spacing_small)
        )

        heightSize = a.getDimension(
                R.styleable.QuantitySetView_setHeight,
                resources.getDimension(R.dimen.spacing_small)
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(widthSize.toInt(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(heightSize.toInt(), MeasureSpec.EXACTLY))
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        //draw-
        paint.strokeWidth = 4f
        paint.color = color
        canvas?.drawLine(paddingHorizontal,
                heightSize / 2,
                paddingHorizontal + buttonSize,
                heightSize / 2,
                paint)

        //draw +
        canvas?.drawLine(
                widthSize - (paddingHorizontal + buttonSize),
                heightSize / 2,
                widthSize - paddingHorizontal,
                heightSize / 2,
                paint)

        canvas?.drawLine(
                widthSize - (paddingHorizontal + buttonSize / 2),
                (heightSize - buttonSize) / 2,
                widthSize - (paddingHorizontal + buttonSize / 2),
                buttonSize + (heightSize - buttonSize) / 2,
                paint
        )

        //draw stroke with corner
        val strokeWidth = 4f
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1f
        val rectF = RectF(
                strokeWidth, strokeWidth, widthSize - strokeWidth,
                heightSize - strokeWidth
        )
        canvas?.drawRoundRect(rectF, 50f, 50f, paint)

        //draw text
        val quantity = "$value/$max"
        paint.textSize = textSize
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.getTextBounds(quantity, 0, quantity.length, bounds)

        canvas?.drawText(
                quantity,
                widthSize / 2 - bounds.width() / 2,
                heightSize / 2 + bounds.height() / 2,
                paint
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        Log.d("ON TOUCH", "x= $x and y= $y")
        if (x != null) {
            if (x < buttonSize + 2 * paddingHorizontal && event.action == MotionEvent.ACTION_UP && value > 0) {
                value--
                invalidate()
            } else if (x > widthSize - (buttonSize + 2 * paddingHorizontal) && event.action == MotionEvent.ACTION_UP && value < max) {
                value++
                invalidate()
            }
        }
        return true
    }
}