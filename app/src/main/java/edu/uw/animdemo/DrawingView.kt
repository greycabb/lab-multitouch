package edu.uw.animdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

import java.util.HashMap

/**
 * A basic custom view for drawing on.
 * @author Joel Ross
 * @version Spring 2016
 */
class DrawingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defaultStyle: Int = 0) : View(context, attrs, defaultStyle) {

    private var viewWidth: Int = 0
    private var viewHeight: Int = 0 //size of the view

    private var bmp: Bitmap? = null //image to draw on

    //drawing values
    private val whitePaint: Paint //drawing variables (pre-defined for speed)

    var ball: Ball? = null //public for easy access

    init {

        viewWidth = 1
        viewHeight = 1 //positive defaults; will be replaced when #onSizeChanged() is called

        //set up drawing variables ahead of time
        whitePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        whitePaint.color = Color.WHITE

    }

    /**
     * Override method that is called when the size of the display is specified (or changes
     * due to rotation).
     */
    public override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //store new size of the view
        viewWidth = w
        viewHeight = h

        //create a properly-sized bitmap to draw on
        bmp = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888)

        ball = Ball((viewWidth / 2).toFloat(), (viewHeight / 2).toFloat(), 100f)

    }

    /**
     * Override this method to specify how size should be determined based on content. See the
     * docs for more details and examples.
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * Override this method to specify drawing.
     */
    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas) //make sure to have the parent do any drawing it is supposed to!

        ball.y += 3f

        canvas.drawColor(Color.rgb(51, 10, 111)) //purple out the background

        canvas.drawCircle(ball.x, ball.y, ball.radius, whitePaint) //we can draw directly onto the canvas

        for (x in 50 until viewWidth - 50) { //most of the width
            for (y in 100..109) { //10 pixels high
                bmp!!.setPixel(x, y, Color.YELLOW) //we can also set individual pixels in a Bitmap (like a BufferedImage)
            }
        }
        canvas.drawBitmap(bmp!!, 0f, 0f, null) //and then draw the BitMap onto the canvas.
        //Canvas bmc = new Canvas(bmp); //we can also make a canvas out of a Bitmap to draw on that (like fetching g2d from a BufferedImage) if we don't want to double-buffer

//        val touches = MainActivity.touches
//
//        forEach (touch in touches.values) {
//
//        }
    }
    companion object {

        private val TAG = "DrawingView"
    }
}
/**
 * We need to override all the constructors, since we don't know which will be called
 * All the constructors eventually call init()
 */
