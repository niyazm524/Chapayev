package view

import ChapayevGame
import core.Game
import javafx.animation.AnimationTimer
import javafx.geometry.Pos
import javafx.scene.CacheHint
import javafx.scene.text.TextAlignment
import tornadofx.*

class GameView : View("Чиртеш") {
    private val game: Game = ChapayevGame()
    override val root = borderpane {
        top = stackpane {
            text {
                styleClass += "title"
                textAlignment = TextAlignment.CENTER
                textProperty().bind(game.getProperty(ChapayevGame::title))
            }
            alignment = Pos.CENTER
            prefHeight = 60.0
        }
        left<PlayerStateFragment>()
        right<PlayerStateFragment>()
        center = game.root
        isCache = true
        isCacheShape = true
        cacheHint = CacheHint.SPEED
    }


    init {
        val timer = object : AnimationTimer() {
            var lastTick: Long = 0L
            var ticksPer5Seconds = 0
            var lastFpsCheck = System.nanoTime()
            override fun handle(now: Long) {
                if (lastTick == 0L) {
                    lastTick = now; return
                }
                ticksPer5Seconds++
                if (now - lastFpsCheck > 3_000_000_000) {
                    title = "Чиртеш | ${ticksPer5Seconds / 3} FPS"
                    ticksPer5Seconds = 0
                    lastFpsCheck = now
                }
                game.update((now - lastTick).toInt() / 1_000_000)
                lastTick = now
            }
        }
        runLater(1.seconds) {
            timer.start()
        }
    }

    override fun onUndock() {
        super.onUndock()
        game.recycle()
    }
}