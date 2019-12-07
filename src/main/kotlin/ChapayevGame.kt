import core.Game
import javafx.scene.canvas.GraphicsContext
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import kotlin.math.sqrt
import kotlin.random.Random

class ChapayevGame(root: Pane) : Game {
    val width = root.prefWidth
    val height = root.prefHeight
    val collidingPairs = mutableListOf<Pair<Checker, Checker>>()
    private val mineCheckers = MutableList(3) { Checker().apply {
        centerX = Random.nextDouble(RADIUS, width-RADIUS)
        centerY = Random.nextDouble(RADIUS, height-RADIUS)
    }}
    init {
        root.children.addAll(mineCheckers)
        mineCheckers[0].centerX = width / 2
        mineCheckers[0].vx = 900.0
    }

    override fun update(elapsedTime: Int) {
        for (checker in mineCheckers) {
           checker.onUpdate(elapsedTime / 500.0, width, height)
        }
        for (checker in mineCheckers) {
            for(target in mineCheckers) {
                if(checker === target) continue
                if(checker overlapsWith target) {
                    collidingPairs.add(checker to target)
                    val dist = sqrt((checker.centerX - target.centerX).sq() + (checker.centerY - target.centerY).sq())
                    val overlap = 0.5 * (dist - RADIUS*2)
                    checker.centerX -= overlap * (checker.centerX - target.centerX) / dist
                    checker.centerY -= overlap * (checker.centerY - target.centerY) / dist
                    target.centerX += overlap * (checker.centerX - target.centerX) / dist
                    target.centerY += overlap * (checker.centerY - target.centerY) / dist
                }
            }
        }


    }

    companion object {
        const val radius = 120.0
    }
}