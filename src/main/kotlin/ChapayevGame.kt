import core.Game
import events.CheckerEvent
import geometry.Rect
import net.GameClient
import net.NetGame
import shapes.Checker
import shapes.Checker.Companion.RADIUS
import tornadofx.*
import view.GamePane
import java.net.InetAddress

class ChapayevGame : Game, NetGame {
    var title: String by property()
    val root = GamePane()
    private val width = root.prefWidth
    private val height = root.prefHeight
    private val bounds = Rect(width / 2 - SIZE / 2, height / 2 - SIZE / 2,
            width / 2 + SIZE / 2, height / 2 + SIZE / 2)
    private val checkers = mutableListOf<Checker>()
    private var netClient = GameClient(this, InetAddress.getByName("192.168.1.7"))

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
        netClient.start()
    }

    private fun onCheckerGone(event: CheckerEvent) {
        val checker = event.target as Checker
        netClient.login("niyaz")
        title = "Checker ${checker.id} is gone!"
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

    fun recycle() {
        netClient.recycle()
    }

    companion object {
        const val SIZE = 520.0
        const val CELL = SIZE / 8
    }
}