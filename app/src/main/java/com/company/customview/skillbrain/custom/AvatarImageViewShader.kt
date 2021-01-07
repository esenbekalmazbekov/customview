package com.company.customview.skillbrain.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.company.customview.R
import com.company.customview.skillbrain.extensions.dpToPx

@SuppressLint("AppCompatCustomView")
class AvatarImageViewShader @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet? = null,
    defStyleAttrs : Int = 0
) : ImageView(context, attrs,defStyleAttrs) {

    companion object {
        private const val DEFAULT_SIZE = 40
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
        private const val DEFAULT_BORDER_WIDTH = 2
    }

    @Px
    var borderWidth = context.dpToPx(DEFAULT_BORDER_WIDTH)
    @ColorInt
    private var borderColor = Color.WHITE
    private var initials = "??"

    private val avatarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val viewRect = Rect()

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageViewMask)

            borderWidth = ta.getDimension(
                R.styleable.AvatarImageViewShader_aivs_borderWidth,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
            )

            borderColor = ta.getColor(
                R.styleable.AvatarImageViewShader_aivs_borderWidth,
                DEFAULT_BORDER_COLOR
            )

            initials = ta.getString(R.styleable.AvatarImageViewMask_aiv_initials) ?: "??"

            ta.recycle()
        }
        scaleType = ScaleType.CENTER_CROP

        setup()
    }

//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        Log.e("AvatarImageViewMask", "onAttachedToWindow")
//    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("AvatarImageViewMask", """
            onMeasure
            width : ${MeasureSpec.toString(widthMeasureSpec)}
            height : ${MeasureSpec.toString(heightMeasureSpec)}
        """.trimIndent())

        val initSize = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(initSize,initSize)
        Log.e("AvatarImageViewMask", "onMeasure after set size: $measuredWidth , $measuredHeight")
    }

    private fun resolveDefaultSize(spec : Int) : Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> { context.dpToPx(DEFAULT_SIZE).toInt() }
            MeasureSpec.AT_MOST -> MeasureSpec.getSize(spec)
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.e("AvatarImageViewMask", "onSizeChanged")

        with(viewRect) {
            left = 0
            top = 0
            right = w
            bottom = h
        }

        prepareShader(w,h)
    }

    private fun prepareShader(w: Int, h: Int) {


        val srcBm = drawable.toBitmap(w, h, Bitmap.Config.ARGB_8888)
        avatarPaint.shader = BitmapShader(srcBm,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
    }

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//        Log.e("AvatarImageViewMask", "onLayout")
//        Log.e("changed", "$changed")
//        Log.e("left", "$left")
//        Log.e("top", "$top")
//        Log.e("right", "$right")
//        Log.e("bottom", "$bottom")
//    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.drawOval(viewRect.toRectF(), avatarPaint)

        val half = (borderWidth / 2).toInt()
        viewRect.inset(half,half)

        canvas?.drawOval(viewRect.toRectF(),borderPaint)
    }

    private fun setup() {

        with(borderPaint) {
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
            color = borderColor
        }
    }
}