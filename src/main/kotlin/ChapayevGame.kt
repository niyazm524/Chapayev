import core.Game
import events.CheckerEvent
import geometry.Rect
import javafx.scene.layout.Pane
import shapes.Board
import shapes.Checker
import shapes.Checker.Companion.RADIUS

class ChapayevGame(root: Pane) : Game {
    val width = root.prefWidth
    val height = root.prefHeight
    val collidingPairs = mutableListOf<Pair<Checker, Checker>>()
    private val bounds = Rect(width / 2 - SIZE / 2, height / 2 - SIZE / 2,
            width / 2 + SIZE / 2, height / 2 + SIZE / 2)
    private val checkers = mutableListOf<Checker>()
    private val board = Board(bounds)


    init {
        val margin = (CELL - RADIUS * 2) / 2
        var lastId = 0
        for (x in 0..7) {
            checkers.add(Checker(++lastId, true,
                    bounds.left + x * CELL + margin + RADIUS,
                    bounds.bottom - margin - RADIUS
            ))
            checkers.add(Checker(++lastId, false,
                    bounds.left + x * CELL + margin + RADIUS,
                    bounds.top + margin + RADIUS
            ))
        }
        root.addEventHandler(CheckerEvent.ON_GONE, ::onCheckerGone)
        root.children.add(board)
        root.children.addAll(checkers)
    }

    private fun onCheckerGone(event: CheckerEvent) {


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