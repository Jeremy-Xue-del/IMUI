package com.example.imui.friend.decoration

import android.graphics.Canvas
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class FriendItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = 0xFFE0E0E0.toInt() // 浅灰色分隔线
        paint.style = Paint.Style.FILL
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val top = child.top - spaceHeight
            c.drawRect(
                left.toFloat(),
                top.toFloat(),
                right.toFloat(),
                (top + spaceHeight).toFloat(),
                paint
            )
        }
    }
}