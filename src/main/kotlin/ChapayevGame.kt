import core.Game
import events.CheckerEvent
import geometry.Rect
import shapes.Checker
import shapes.Checker.Companion.RADIUS
import tornadofx.*
import view.GamePane

class ChapayevGame : Game {
    var title: String by property()
    private val width = SIZE + 60
    private val height = SIZE + 60
    private val bounds = Rect(width / 2 - SIZE / 2, height / 2 - SIZE / 2,
            width / 2 + SIZE / 2, height / 2 + SIZE / 2)
    override val root = GamePane(bounds)
    private val checkers = mutableListOf<Checker>()

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
        root.children.addAll(checkers)
        ChapayevClient.start()
    }

    private fun onCheckerGone(event: CheckerEvent) {
        val checker = event.target as Checker
        checker.fade()
        title = "Checker ${checker.id} is gone!"
    }

    override fun update(elapsedTime: Int) {
        for (i in 0 until checkers.size)
            for (j in (i + 1) until checkers.size)
                if (checkers[i] collidesWith checkers[j]) {
                    checkers[i].resolveCollisionWith(checkers[j])
                }

        for (checker in checkers) {
            checker.onUpdate(elapsedTime.toDouble(), bounds)
            checker.syncPos()
        }
    }

    override fun recycle() {
        ChapayevClient.recycle()
    }

    companion object {
        const val SIZE = 520.0
        const val CELL = SIZE / 8
    }
}