package com.anwesh.uiprojects.rotoppositeblockview

/**
 * Created by anweshmishra on 19/09/20.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color
import android.content.Context
import android.app.Activity

val colors : Array<Int> = arrayOf(
        "#F44336",
        "#009688",
        "#3F51B5",
        "#4CAF50",
        "#03A9F4"
).map({Color.parseColor(it)}).toTypedArray()
val strokeFactor : Float = 90f
val sizeFactor : Float = 4.9f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val parts : Int = 3
val scGap : Float = 0.02f / parts
val rot : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawRotOppositeBlock(scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val sf1 : Float = scale.divideScale(0, parts)
    val sf2 : Float = scale.divideScale(1, parts)
    val sf3 : Float = scale.divideScale(2, parts)
    val y : Float = (h / 2 - w / 2) * sf2
    val size : Float = Math.min(w, h) / sizeFactor
    val rSize : Float = size * sf1
    save()
    translate(w / 2, h / 2)
    rotate(rot * sf3)
    for (j in 0..1) {
        save()
        scale(1f, 1f - 2 * j)
        translate(w / 2 - size / 2, y)
        drawRect(RectF(-rSize / 2, 0f, rSize / 2, rSize), paint)
        restore()
    }
    restore()
}

fun Canvas.drawROBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    drawRotOppositeBlock(scale, w, h, paint)
}

class RotOppositeBlockView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}