package com.marcosrocha85.events.application.utils

import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun calculateDistance(fromLatitude: Double, fromLongitude: Double,
                      toLatitude: Double, toLongitude: Double): Double {
    val earthRadius = 6372.8
    val dLat = Math.toRadians(toLatitude - fromLatitude)
    val dLon = Math.toRadians(toLongitude - fromLongitude)
    val rFromLatitude = Math.toRadians(fromLatitude)
    val rFromLongitude = Math.toRadians(fromLongitude)

    val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(rFromLatitude) * cos(rFromLongitude)
    val c = 2 * asin(sqrt(a))
    return earthRadius * c
}