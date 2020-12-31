package com.example.test

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup


fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

val Int.toDp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

class CircleImageView : androidx.appcompat.widget.AppCompatImageView {
    private var mPaint //画笔
            : Paint? = Paint()
    private var mRadius : Float //圆形图片的半径
            = 0f
    private var mScale //图片的缩放比例
            = 0f

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //因为是圆形图片，所以应该让宽高保持一致
        val size: Int = measuredWidth.coerceAtMost(measuredHeight)
        mRadius = (size / 2).toFloat()
        setMeasuredDimension(size, size)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val bitmap = drawableToBitmap(drawable)
        //初始化BitmapShader，传入bitmap对象
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        //计算缩放比例
        mScale = mRadius * 2.0f / bitmap.height.coerceAtMost(bitmap.width)
        val matrix = Matrix()
        matrix.setScale(mScale, mScale)
        bitmapShader.setLocalMatrix(matrix)
        mPaint!!.shader = bitmapShader

        //画圆形，指定好中心点坐标、半径、画笔
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint!!)
    }

    //写一个drawable转BitMap的方法
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val w = if (drawable.intrinsicWidth > 0) {
            drawable.intrinsicWidth
        } else {1}
        val h = if (drawable.intrinsicHeight > 0) {
            drawable.intrinsicHeight
        } else {1}
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, w, h)
        drawable.draw(canvas)
        return bitmap
    }
}