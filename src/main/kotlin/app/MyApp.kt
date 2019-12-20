package app

import ChapayevClient
import tornadofx.*
import view.MenuView

class MyApp : App(MenuView::class, Styles::class) {
    override fun stop() {
        super.stop()
        ChapayevClient.recycle()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<MyApp>(args)
        }
    }
}