package app

import tornadofx.*
import view.GameView

class MyApp : App(GameView::class, Styles::class) {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<MyApp>(args)
        }
    }
}