package edu.uw.animdemo

/**
 * A simple struct to hold a shape
 */
class Ball(var x: Float //center
           , var y: Float, var radius: Float //radius
) {
    var dx: Float = 0.toFloat() //velocity
    var dy: Float = 0.toFloat()

    init {
        this.dx = 0f
        this.dy = 0f
    }
}
