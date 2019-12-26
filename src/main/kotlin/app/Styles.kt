package app

import javafx.scene.effect.InnerShadow
import javafx.scene.layout.BackgroundRepeat
import javafx.scene.layout.BackgroundSize
import javafx.scene.paint.Color
import tornadofx.*
import java.net.URI

class Styles : Stylesheet() {
    companion object {
        val own by cssclass()
        val mate by cssclass()
        val invisible by cssclass()
        val title by cssclass()
        val menuPane by cssclass("menu_pane")
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

        title {
            fontSize = Dimension(18.0, Dimension.LinearUnits.pt)
        }

        menuPane {
            backgroundImage += URI("/background.jpg")
            backgroundSize += BackgroundSize(100.0, 100.0, true, true, false, true)
            backgroundRepeat += BackgroundRepeat.NO_REPEAT to BackgroundRepeat.NO_REPEAT
        }
    }
}