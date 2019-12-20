package view

import geometry.Rect
import javafx.scene.CacheHint
import javafx.scene.layout.Pane
import shapes.Board

class GamePane(val bounds: Rect) : Pane() {
    val board: Board = Board(bounds)

    init {
        prefWidth = bounds.width() + 60
        prefHeight = bounds.height() + 60
        isCache = true
        isCacheShape = true
        cacheHint = CacheHint.SPEED
        children += board
    }

}