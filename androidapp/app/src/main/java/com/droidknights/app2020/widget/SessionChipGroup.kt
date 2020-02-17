package com.droidknights.app2020.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import kotlin.math.max
import com.droidknights.app2020.R

class SessionChipGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val views = mutableListOf<List<View>>()
    private var lineViews = mutableListOf<View>()
    private val lineHeights = mutableListOf<Int>()
    private val lineWidths = mutableListOf<Int>()
    @FlowGravity
    private var gravity: Int = START
    private var startMargin: Float
    private var endMargin: Float
    private var topMargin: Float
    private var bottomMargin: Float

    companion object {
        @IntDef(START, CENTER, END)
        @Retention(AnnotationRetention.SOURCE)
        annotation class FlowGravity

        const val START = -1
        const val CENTER = 0
        const val END = 1
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SessionChipGroup)
        gravity = typedArray.getInt(R.styleable.SessionChipGroup_chipGravity, -1)
        startMargin = typedArray.getDimension(R.styleable.SessionChipGroup_itemMargin, dip2px(context, 0f))
        endMargin = typedArray.getDimension(R.styleable.SessionChipGroup_itemMargin, dip2px(context, 4f))
        topMargin = typedArray.getDimension(R.styleable.SessionChipGroup_itemMargin, dip2px(context, 4f))
        bottomMargin = typedArray.getDimension(R.styleable.SessionChipGroup_itemMargin, dip2px(context, 4f))

        typedArray.recycle()
    }

    fun setGravity(@FlowGravity gravity: Int) {
        this.gravity = gravity
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        var width = 0
        var height = 0
        var lineWidth = 0
        var lineHeight = 0

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
            val lp = childView.layoutParams as MarginLayoutParams

            val childWidth = childView.measuredWidth + lp.leftMargin + lp.rightMargin
            val childHeight = childView.measuredHeight + lp.topMargin + lp.bottomMargin

            childView.layoutParams = lp

            if (lineWidth + childWidth > widthSpecSize - paddingStart - paddingEnd) {
                width = max(lineWidth, width)
                lineWidth = childWidth
                lineHeight = childHeight
                height += lineHeight
            } else {
                lineWidth += childWidth
                lineHeight = max(lineHeight, childHeight)
            }

            if (i == childCount - 1) {
                width = max(lineWidth, width)
                height += lineHeight
            }
        }

        setMeasuredDimension(
            if (widthSpecMode == MeasureSpec.EXACTLY) widthSpecSize else width + paddingStart + paddingEnd,
            if (heightSpecMode == MeasureSpec.EXACTLY) heightSpecSize else height + paddingTop + paddingBottom
        )
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        views.clear()
        lineViews.clear()
        lineHeights.clear()
        lineWidths.clear()

        var lineWidth = 0
        var lineHeight = 0

        for (i in 0 until childCount) {
            val childView = getChildAt(i)

            if (childView.visibility == View.GONE) {
                continue
            }
            val lp = childView.layoutParams as MarginLayoutParams

            val childWidth = childView.measuredWidth
            val childHeight = childView.measuredHeight

            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - paddingStart - paddingEnd) {
                views.add(lineViews)
                lineWidths.add(lineWidth)
                lineHeights.add(lineHeight)

                lineWidth = 0
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin
                lineViews = mutableListOf()
            }

            lineWidth += (childWidth + lp.leftMargin + lp.rightMargin)
            lineHeight = max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin)
            lineViews.add(childView)
        }

        lineWidths.add(lineWidth)
        lineHeights.add(lineHeight)
        views.add(lineViews)

        var top = paddingTop
        var left: Int

        for (i in 0 until views.size) {
            val list = views[i]
            val currentWidth = lineWidths[i]
            left = when (gravity) {
                START -> paddingStart
                CENTER -> (width - currentWidth) / 2 + paddingStart
                else -> {
                    list.reversed()
                    width - currentWidth - paddingStart - paddingEnd
                }
            }

            lineHeight = lineHeights[i]

            for (child in list) {
                if (child.visibility == View.GONE) {
                    continue
                }
                val lp = child.layoutParams as MarginLayoutParams

                val cleft = lp.leftMargin + left
                val cRight = cleft + child.measuredWidth
                val cTop = lp.topMargin + top
                val cBottom = cTop + child.measuredHeight
                left += (child.measuredWidth + lp.leftMargin
                        + lp.rightMargin)
                child.layout(cleft, cTop, cRight, cBottom)
            }

            top += lineHeight
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        val lp = MarginLayoutParams(context, attrs)
        lp.leftMargin = startMargin.toInt()
        lp.rightMargin = endMargin.toInt()
        lp.topMargin = topMargin.toInt()
        lp.bottomMargin = bottomMargin.toInt()
        return lp
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        val lp = MarginLayoutParams(p)
        lp.leftMargin = startMargin.toInt()
        lp.rightMargin = endMargin.toInt()
        lp.topMargin = topMargin.toInt()
        lp.bottomMargin = bottomMargin.toInt()
        return lp
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        val lp = MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lp.leftMargin = startMargin.toInt()
        lp.rightMargin = endMargin.toInt()
        lp.topMargin = topMargin.toInt()
        lp.bottomMargin = bottomMargin.toInt()
        return lp
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }
}