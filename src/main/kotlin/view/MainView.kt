package view

import ChapayevGame
import core.Game
import javafx.animation.AnimationTimer
import javafx.scene.CacheHint
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = pane {
        prefWidth = 800.0
        prefHeight = 600.0
        isCache = true
        isCacheShape = true
        cacheHint = CacheHint.SPEED
    }
    private val game: Game = ChapayevGame(root)

    init {
        val timer = object : AnimationTimer() {
            var lastTick: Long = 0L
            override fun handle(now: Long) {
                if (lastTick == 0L) {
                    lastTick = now; return
                }
                game.update(16)
                lastTick = now
            }
        }
        runLater(1.seconds) {
            timer.start()
        }
    }
}