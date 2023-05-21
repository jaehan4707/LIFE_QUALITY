package com.example.myapplication.edu

import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class ZoomableImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val matrix: Matrix = Matrix()
    private val matrixValues = FloatArray(9)
    private var mode = NONE
    private val startPoint = PointF()
    private var startDistance = 0f
    private var midPoint = PointF()
    private var zoomSpeed = 0.03f
    private var initialImageDrawable: Drawable? = null
    private val initialMatrix: Matrix = Matrix()

    companion object {
        private const val NONE = 0
        private const val DRAG = 1
        private const val ZOOM = 2
    }

    init {
        // 원본 이미지를 설정하고 초기 이미지와 초기 행렬을 저장
        setImageDrawable(drawable) // drawable은 원본 이미지 Drawable 객체입니다.
        //initialImageDrawable = drawable
        //initialMatrix.set(imageMatrix)
       // setInitialImageDrawable(drawable)
        adjustImageBounds()
    }
    private fun adjustImageBounds() {
        val drawable = drawable ?: return
        val drawableWidth = drawable.intrinsicWidth
        val drawableHeight = drawable.intrinsicHeight
        val viewWidth = width
        val viewHeight = height

        val scaleX = viewWidth.toFloat() / drawableWidth.toFloat()
        val scaleY = viewHeight.toFloat() / drawableHeight.toFloat()
        val scale = scaleX.coerceAtMost(scaleY)

        val scaledWidth = (drawableWidth * scale).toInt()
        val scaledHeight = (drawableHeight * scale).toInt()

        val offsetX = (viewWidth - scaledWidth) / 2
        val offsetY = (viewHeight - scaledHeight) / 2

        val matrix = imageMatrix
        matrix.reset()
        matrix.setScale(scale, scale)
        matrix.postTranslate(offsetX.toFloat(), offsetY.toFloat())
        imageMatrix = matrix
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        adjustImageBounds()
    }
    fun setInitialImageDrawable(drawable: Drawable?) {
        Log.d("test","초기화면")
        initialImageDrawable = drawable
        setImageDrawable(drawable)
        initialMatrix.set(imageMatrix)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("test","터치이벤트")

        //Log.d("test","$startPoint")
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                mode = DRAG
                startPoint.set(event.x, event.y)
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                mode = ZOOM
                startDistance = calculateDistance(event)
                calculateMidPoint(event)
            }
            MotionEvent.ACTION_MOVE -> {
                if (mode == DRAG) {
                    val dx = event.x - startPoint.x
                    val dy = event.y - startPoint.y
                    matrix.postTranslate(dx, dy)
                    startPoint.set(event.x, event.y)
                } else if (mode == ZOOM) {
                    val currentDistance = calculateDistance(event)
                    if (currentDistance > 10f) {
                        val scale = currentDistance / startDistance
                        matrix.getValues(matrixValues)
                        val currentScale = matrixValues[Matrix.MSCALE_X]
                        if (scale * currentScale > 0.5f && scale * currentScale < 3f) {
                            matrix.postScale(scale, scale, midPoint.x, midPoint.y)
                        }
                    }
                }
                imageMatrix = matrix
            }
            MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_UP -> {
                mode = NONE
            }
        }
        return true
    }

    private fun calculateDistance(event: MotionEvent): Float {
        val dx = event.getX(0) - event.getX(1)
        val dy = event.getY(0) - event.getY(1)
        return Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat() * zoomSpeed
    }

    private fun calculateMidPoint(event: MotionEvent) {
        val x = (event.getX(0) + event.getX(1)) / 2 * zoomSpeed
        val y = (event.getY(0) + event.getY(1)) / 2 * zoomSpeed
        midPoint.set(x, y)
        //Log.d("test","midpoint: ${midPoint}")
    }
}