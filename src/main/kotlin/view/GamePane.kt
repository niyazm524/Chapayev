package view

import geometry.Rect
import javafx.scene.CacheHint
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import shapes.Board
import tornadofx.*

class GamePane(val bounds: Rect) : Pane() {
    val board: Board

    init {
        prefWidth = 580.0
        prefHeight = 580.0
        minWidth = 440.0
        minHeight = 440.0
        maxWidth = 720.0
        maxHeight = 720.0
        isCache = true
        isCacheShape = true
        cacheHint = CacheHint.SPEED
        board = Board(bounds)
        children += board
        paddingAll = 20.0
        background = Background(BackgroundFill(Color.LIGHTSKYBLUE, null, null))
    }

}