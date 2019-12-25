package view

import ChapayevGame.Companion.SIZE
import geometry.Rect
import javafx.scene.CacheHint
import javafx.scene.layout.Pane
import shapes.Board
import tornadofx.*

class GamePane : Pane() {
    val board: Board
    val bounds: Rect

    init {
        prefWidth = 580.0
        prefHeight = 580.0
        minWidth = 440.0
        minHeight = 440.0
        maxWidth = 720.0
        maxHeight = 720.0
        bounds = calcBounds(prefWidth, prefHeight)
        isCache = true
        isCacheShape = true
        cacheHint = CacheHint.SPEED
        board = Board(bounds)
        children += board
        paddingAll = 20.0
        //background = Background(BackgroundFill(Color.LIGHTSKYBLUE, null, null))
    }

    private fun calcBounds(w: Double, h: Double) = Rect(w / 2 - SIZE / 2, h / 2 - SIZE / 2,
            w / 2 + SIZE / 2, h / 2 + SIZE / 2)

}