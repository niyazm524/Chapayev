package extensions

import ChapayevGame
import ChapayevGame.Companion.CELL
import shapes.Checker

fun ChapayevGame.generateCheckers(levels: Pair<Int, Int>): Map<Int, Checker> {
    val checkersMap = hashMapOf<Int, Checker>()
    val margin = (CELL - Checker.RADIUS * 2) / 2
    var lastId = 0
    for (x in 0..7) {
        checkersMap[++lastId] = Checker(lastId, true,
                bounds.left + x * CELL + margin + Checker.RADIUS,
                bounds.bottom - margin - Checker.RADIUS - (levels.first - 1) * CELL
        )
        checkersMap[++lastId] = Checker(lastId, false,
                bounds.left + x * CELL + margin + Checker.RADIUS,
                bounds.top + margin + Checker.RADIUS + (levels.second - 1) * CELL
        )
    }
    return checkersMap
}