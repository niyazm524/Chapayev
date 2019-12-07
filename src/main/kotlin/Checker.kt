import javafx.scene.effect.DropShadow
import javafx.scene.effect.Shadow
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import kotlin.math.abs

const val RADIUS = 20.0
const val FRICTION = 0.8

fun Double.sq() = this * this

class Checker : Circle(RADIUS) {
    var vx: Double = 0.0
    var vy: Double = 0.0
    var ax: Double = 0.0
    var ay: Double = 0.0
    val mass: Double = 10.0

    init {
        fill = Color.DARKGREY
        effect = DropShadow(3.0, Color.BLUE)
        setOnMouseClicked { event: MouseEvent ->
            vx = (centerX - event.x) * 30
            vy = (centerY - event.y) * 30
        }
        //stroke = Color.DARKBLUE
    }

    fun onUpdate(elapsedTime: Double, width: Double, height: Double) {
        ax = -vx * FRICTION
        ay = -vy * FRICTION
        vx += ax * elapsedTime
        vy += ay * elapsedTime
        centerX += vx * elapsedTime
        centerY += vy * elapsedTime

        when {
            centerX - RADIUS <= 0 -> {
                ax = -ax; vx = -vx
            }
            centerY - RADIUS <= 0 -> {
                ay = -ay; vy = -vy
            }
            centerX + RADIUS >= width -> {
                ax = -ax; vx = -vx
            }
            centerY + RADIUS >= height -> {
                ay = -ay; vy = -vy
            }
        }

        if (abs(vx * vx + vy * vy) < 0.01) {
            vx = 0.0
            vy = 0.0
        }
    }

    infix fun overlapsWith(target: Checker): Boolean =
            abs((centerX - target.centerX).sq() + (centerY - target.centerY).sq()) <= RADIUS.sq()

}