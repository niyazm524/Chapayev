import core.Game
import events.CheckerClickEvent
import events.CheckerEvent
import extensions.generateCheckers
import shapes.Checker
import tornadofx.*
import view.GamePane

class ChapayevGame : Game {
    var title: String by property()
    override val root = GamePane()
    val bounds = root.bounds
    private val checkers: Map<Int, Checker>
    private val checkersArray: Array<Checker>

    init {
        checkers = generateCheckers(3 to 2)
        checkersArray = checkers.values.toTypedArray()
        root.addEventHandler(CheckerEvent.ON_GONE, ::onCheckerGone)
        root.addEventHandler(CheckerClickEvent.ON_CLICK, ::onCheckerClick)
        root.children.addAll(checkers.values)
    }

    private fun onCheckerGone(event: CheckerEvent) {
        val checker = event.target as Checker
        checker.fade()
        title = "Checker ${checker.id} is gone!"
    }

    private fun onCheckerClick(event: CheckerClickEvent) {
        val checker = event.target as Checker
        checker.vel.x = event.x * Checker.FORCE
        checker.vel.y = event.y * Checker.FORCE
    }

    override fun update(elapsedTime: Int) {
        for (i in checkersArray.indices)
            for (j in (i + 1) until checkersArray.size)
                if (checkersArray[i] collidesWith checkersArray[j]) {
                    checkersArray[i].resolveCollisionWith(checkersArray[j])
                }

        for (checker in checkersArray) {
            checker.onUpdate(elapsedTime.toDouble(), bounds)
            checker.syncPos()
        }
    }

    companion object {
        const val SIZE = 520.0
        const val CELL = SIZE / 8
    }
}