package view

import javafx.scene.CacheHint
import javafx.scene.layout.Pane
import shapes.Board

class GamePane : Pane() {
    val board: Board = Board(bounds)

    init {
        isCache = true
        cacheHint = CacheHint.SPEED
        prefWidth = 610.0
        prefHeight = 610.0
        isCache = true
        isCacheShape = true
        cacheHint = CacheHint.SPEED
        children += board
    }

}