package com.droidknights.app2020.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.droidknights.app2020.R


class SessionChip @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    var chipBackgroundColor = 0
        set(value) {
            field = value
            buildView()
        }

    var chipTextColor = 0
        set(value) {
            field = value
            buildView()
        }

    var cornerRadius = 0
        set(value) {
            field = value
            buildView()
        }
    var strokeSize = 0
        set(value) {
            field = value
            buildView()
        }
    var strokeColor = 0
        set(value) {
            field = value
            buildView()
        }

init {
        initTypedArray(attrs)
        buildView()
        setSingleLine()
        ellipsize = TextUtils.TruncateAt.END
    }

    private fun initTypedArray(attrs: AttributeSet?) {
        if (attrs == null || context == null) return

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.SessionChip, 0, 0)
        //백그라운드 컬러는 글자색에서 HSL50 컬러이다.
        chipBackgroundColor =
            typedArray.getColor(
                R.styleable.SessionChip_sc_textColor,
                ContextCompat.getColor(context, R.color.color_sessionChipText)
            ).let{
                when(it){
                Color.WHITE -> Color.BLACK
                Color.BLACK -> Color.WHITE
                else -> lighten(it, 30)
                }
            }


        chipTextColor = typedArray.getColor(
            R.styleable.SessionChip_sc_textColor,
            ContextCompat.getColor(context, R.color.color_sessionChipText)
        )
        cornerRadius = typedArray.getDimensionPixelSize(
            R.styleable.SessionChip_sc_cornerRadius,
            resources.getDimensionPixelSize(R.dimen.SessionChip_height) / 2
        )
        strokeSize = typedArray.getDimensionPixelSize(R.styleable.SessionChip_sc_strokeSize, 0)
        //stroke 컬러는 글자색과 같다.
        strokeColor = typedArray.getColor(
            R.styleable.SessionChip_sc_textColor,
            ContextCompat.getColor(context, R.color.color_sessionChipText)
        )


        typedArray.recycle()

    }

    private fun buildView() {
        createPaddings()
        createChipText()
        createBackground()
    }

    private fun createPaddings() {
        gravity = Gravity.CENTER
        val startPadding = resources.getDimensionPixelSize(R.dimen.SessionChip_horizontal_margin)
        val topPadding = resources.getDimensionPixelSize(R.dimen.SessionChip_vertical_margin)
        val endPadding = resources.getDimensionPixelSize(R.dimen.SessionChip_horizontal_margin)
        val bottomPadding = resources.getDimensionPixelSize(R.dimen.SessionChip_vertical_margin)
        setPaddingRelative(startPadding, topPadding, endPadding, bottomPadding)
    }

    private fun createChipText() {
        setTextColor(chipTextColor)
    }


    private fun createBackground() {
        val radius = cornerRadius.toFloat()
        val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)

        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadii = radii
            setColor(chipBackgroundColor)
            setStroke(strokeSize, strokeColor)
        }
    }

    //util

    /**
     * Darkens a given color.
     *
     * @param base base color
     * @param amount amount between 0 and 100
     * @return darken color
     */
    private fun darken(base: Int, amount: Int): Int {
        var hsv = FloatArray(3)
        Color.colorToHSV(base, hsv)

        val hsl = hsv2hsl(hsv)
        hsl[2] -= amount / 100f

        if (hsl[2] < 0)
            hsl[2] = 0f

        hsv = hsl2hsv(hsl)
        return Color.HSVToColor(hsv)
    }

    /**
     * lightens a given color
     * @param base base color
     * @param amount amount between 0 and 100
     * @return lightened
     */
    fun lighten(base: Int, amount: Int): Int {
        var hsv = FloatArray(3)
        Color.colorToHSV(base, hsv)

        val hsl = hsv2hsl(hsv)
        hsl[2] += amount / 100f

        if (hsl[2] > 1)
            hsl[2] = 1f

        hsv = hsl2hsv(hsl)
        return Color.HSVToColor(hsv)
    }

    /**
     * Converts HSV (Hue, Saturation, Value) color to HSL (Hue, Saturation, Lightness)
     * https://gist.github.com/xpansive/1337890
     *
     * @param hsv HSV color array
     * @return hsl
     */
    private fun hsv2hsl(hsv: FloatArray): FloatArray {
        val hue = hsv[0]
        val sat = hsv[1]
        val `val` = hsv[2]

        val nhue = (2f - sat) * `val`
        var nsat = sat * `val` / if (nhue < 1f) nhue else 2f - nhue
        if (nsat > 1f)
            nsat = 1f

        return floatArrayOf(hue, nsat, nhue / 2f)
    }

    /**
     * Reverses hsv2hsl
     * https://gist.github.com/xpansive/1337890
     *
     * @param hsl HSL color array
     * @return hsv color array
     */
    private fun hsl2hsv(hsl: FloatArray): FloatArray {
        val hue = hsl[0]
        var sat = hsl[1]
        val light = hsl[2]

        sat *= if (light < .5) light else 1 - light

        return floatArrayOf(hue, 2f * sat / (light + sat), light + sat)
    }

}