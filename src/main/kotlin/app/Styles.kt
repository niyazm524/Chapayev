package app

import javafx.scene.effect.InnerShadow
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val own by cssclass()
        val mate by cssclass()
    }

    init {
        own {
            fill = Color.DARKRED
            effect = InnerShadow(18.0, Color.WHITE)
            stroke = Color.GRAY
        }

        mate {
            fill = Color.DARKBLUE
            effect = InnerShadow(18.0, Color.WHITE)
            stroke = Color.GRAY
        }
    }
}