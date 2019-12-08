import core.Game
import javafx.scene.layout.Pane
import kotlin.random.Random

class ChapayevGame(root: Pane) : Game {
    val width = root.prefWidth
    val height = root.prefHeight
    val collidingPairs = mutableListOf<Pair<Checker, Checker>>()
    private val checkers = MutableList(3) {
        Checker(
                Random.nextDouble(RADIUS, width - RADIUS),
                Random.nextDouble(RADIUS, height - RADIUS)
        )
    }

    init {
        root.children.addAll(checkers)
        checkers[0].pos.x = width / 2
        checkers[0].vel.x = 900.0
        checkers[0].syncPos()
    }

    override fun update(elapsedTime: Int) {
        for (i in 0 until checkers.size)
            for (j in (i + 1) until checkers.size)
                if (checkers[i] collidesWith checkers[j]) {
                    checkers[i].resolveCollisionWith(checkers[j])
                }

        for (checker in checkers) {
            checker.onUpdate(1.0, width, height)
            checker.syncPos()
        }
    }
}