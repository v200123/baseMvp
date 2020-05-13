package com.example.newlocation02

import android.animation.TimeInterpolator
import kotlin.math.pow

class BreathingInterpolator : TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
       val x:Float = 6 * input
        val k:Float = 1.0f / 3
        val t = 6
        val  n = 1//控制函数周期，这里取此函数的第一个周期
         val  PI = 3.1416f
        var output = 0.0

        if (x >= ((n - 1) * t) && x < ((n - (1 - k)) * t)) {
            output =  0.5 * Math.sin(((PI / (k * t)) * ((x - k * t / 2) - (n - 1) * t)).toDouble()) + 0.5f
        } else if (x >= (n - (1 - k)) * t && x < n * t) {
            output =
                (0.5 * Math.sin(((PI / ((1 - k) * t)) * ((x - (3 - k) * t / 2) - (n - 1) * t)).toDouble()) + 0.5).pow(
                    2
                )
        }
        return output.toFloat()
    }
}