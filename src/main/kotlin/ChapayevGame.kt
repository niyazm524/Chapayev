import core.Game
import javafx.scene.CacheHint
import javafx.scene.effect.DropShadow
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Rectangle

class ChapayevGame(root: Pane) : Game {
    val width = root.prefWidth
    val height = root.prefHeight
    val collidingPairs = mutableListOf<Pair<Checker, Checker>>()
    private val bounds = Rect(width / 2 - SIZE / 2, height / 2 - SIZE / 2,
            width / 2 + SIZE / 2, height / 2 + SIZE / 2)
    private val checkers = mutableListOf<Checker>()
    private val platform = Rectangle().apply {
        width = SIZE
        height = SIZE
        x = bounds.left
        y = bounds.top
        fill = ImagePattern(
                Image("/checkered.jpg"),
                x, y, 260.0, 260.0,
                false
        )
        effect = DropShadow(12.0, Color.GREY)
        isCache = true
        cacheHint = CacheHint.SPEED
    }


    init {
        val margin = (CELL - RADIUS * 2) / 2
        for (x in 0..7) {
            checkers.add(Checker(true,
                    bounds.left + x * CELL + margin + RADIUS,
                    bounds.bottom - margin - RADIUS
            ))
            checkers.add(Checker(false,
                    bounds.left + x * CELL + margin + RADIUS,
                    bounds.top + margin + RADIUS
            ))
        }
        root.children.add(platform)
        root.children.addAll(checkers)
    }

    override fun update(elapsedTime: Int) {
        for (i in 0 until checkers.size)
            for (j in (i + 1) until checkers.size)
                if (checkers[i] collidesWith checkers[j]) {
                    checkers[i].resolveCollisionWith(checkers[j])
                }

        for (checker in checkers) {
            checker.onUpdate(1.0, bounds)
            checker.syncPos()
        }
    }

    companion object {
        const val SIZE = 520.0
        const val CELL = SIZE / 8
    }
}