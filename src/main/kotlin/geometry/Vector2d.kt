package geometry

import kotlin.math.sqrt

data class Vector2d(var x: Double = 0.0, var y: Double = 0.0) {
    fun length() = sqrt(x * x + y * y)
    fun normalize(): Vector2d {
        val len = length()
        return if (len == 0.0) Vector2d() else Vector2d(x / len, y / len)
    }

    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)
    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)
    operator fun times(number: Double) = Vector2d(x * number, y * number)
    operator fun plusAssign(other: Vector2d) {
        x += other.x
        y += other.y
    }

    operator fun minusAssign(other: Vector2d) {
        x -= other.x
        y -= other.y
    }

    operator fun minusAssign(value: Double) {
        x -= value
        y -= value
    }

    operator fun timesAssign(value: Double) {
        x *= value
        y *= value
    }

    operator fun unaryMinus() = Vector2d(-x, -y)

    fun reset() {
        x = 0.0
        y = 0.0
    }

    fun dot(other: Vector2d): Double = x * other.x + y * other.y

    fun set(new: Vector2d) {
        x = new.x
        y = new.y
    }
}