package com.droidknights.app2020.ui.schedule.detail

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.droidknights.app2020.R

class SessionDetailDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val divider = ContextCompat.getDrawable(context, R.drawable.session_detail_divider)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        for (i in 1 until parent.childCount) {
            val child = parent.getChildAt(i)

            divider?.let {
                it.setBounds(
                    0, child.top,
                    parent.width, child.top + divider.intrinsicHeight
                )
                it.draw(c)
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position > 0) {
            outRect.top = divider?.intrinsicHeight ?: 0
        }
    }
}