package com.droidknights.app2020.ui.home

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R
import com.droidknights.app2020.util.dp2px

class HistoryItemDecoration : RecyclerView.ItemDecoration() {

    private val edgeSize = 16.dp2px()

    private val paint = Paint(ANTI_ALIAS_FLAG).apply {
        color = 0xFFF1F1F1.toInt()
        strokeWidth = 1.dp2px().toFloat()
    }
    private val offset = 1.dp2px() / 2f

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter as? HomeAdapter ?: return

        var isStartedEvent = false
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (position < 0) {
                continue
            }

            if (isStartedEvent) {
                val y = child.y - offset
                c.drawLine(
                    child.x + edgeSize,
                    y,
                    child.x + child.width - edgeSize,
                    y,
                    paint
                )
            } else {
                isStartedEvent = adapter.getItemViewType(position) == R.layout.item_home_history
            }
        }
    }
}