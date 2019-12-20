package core

import javafx.scene.layout.Pane

interface Game {
    val root: Pane
    fun update(elapsedTime: Int)
    fun recycle() {}
}