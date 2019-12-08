package shapes

import extensions.sq
import geometry.Rect
import geometry.Vector2d
import javafx.scene.CacheHint
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Circle
import kotlin.math.abs


class Checker(var isOwn: Boolean = true, x: Double = 0.0, y: Double = 0.0) : Circle(RADIUS) {
    val pos = Vector2d(x, y)
    val vel = Vector2d()
    val acc = Vector2d()
    val mass: Double = 10.0

    init {
        if (isOwn) styleClass.add("own")
        else styleClass.add("mate")
        isCache = true
        cacheHint = CacheHint.SPEED
        setOnMouseClicked { event: MouseEvent ->
            vel.x = (pos.x - event.x) * 400
            vel.y = (pos.y - event.y) * 400
        }
        syncPos()
    }

    fun onUpdate(elapsedMs: Double, bounds: Rect) {
        //acc.set(vel * FRICTION)
        //vel += acc * elapsedMs
        pos += vel * (elapsedMs / 1000)
        if (vel.x > 0.01) vel.x *= FRICTION
        else if (vel.x < 0.01) vel.x *= FRICTION
        if (vel.y > 0.01) vel.y *= FRICTION
        else if (vel.y < 0.01) vel.y *= FRICTION

        if (pos.x - RADIUS <= bounds.left || pos.x + RADIUS >= bounds.right) {
            acc.x = -acc.x; vel.x = -vel.x * 0.9
        }
        if (pos.y - RADIUS <= bounds.top || pos.y + RADIUS >= bounds.bottom) {
            acc.y = -acc.y; vel.y = -vel.y * 0.9
        }

        if (vel.length() < 0.01) {
            vel.reset()
        }
    }

    infix fun collidesWith(target: Checker): Boolean =
            abs((pos.x - target.pos.x).sq() + (pos.y - target.pos.y).sq()) <= (RADIUS * 2).sq()

    fun resolveCollisionWith(checker: Checker) {
        val delta = pos - checker.pos
        val dist = delta.length()
        val minTranslationDist = delta * ((RADIUS * 2 - dist) / dist)
        val m1 = 1 / mass
        val m2 = 1 / checker.mass

        pos += minTranslationDist * (m1 / (m1 + m2))
        checker.pos -= minTranslationDist * (m2 / (m1 + m2))

        // impact speed
        val dv = vel - checker.vel
        val dvn = dv.dot(minTranslationDist.normalize())
        // sphere intersecting but moving away from each other already
        if (dvn > 0.0) return

        // collision impulse
        val i = (-(1.0 + RESTITUTION) * dvn) / (m1 + m2)
        val impulse = minTranslationDist.normalize() * i

        // change in momentum
        vel += impulse * m1
        checker.vel -= impulse * m2
    }

    fun syncPos() {
        centerX = pos.x
        centerY = pos.y
    }

    companion object {
        const val RADIUS = 28.0
        const val FRICTION = 0.98
        const val RESTITUTION = 0.4
    }
}