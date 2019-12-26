package app

import ChapayevClient
import server.ChapayevServer
import tornadofx.*
import view.MenuView

class MyApp : App(MenuView::class, Styles::class) {

    override fun stop() {
        super.stop()
        ChapayevClient.recycle()
        ChapayevServer.recycle()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ChapayevClient.start()
            ChapayevServer.start()
            launch<MyApp>(args)
        }
    }

    override fun init() {
        super.init()
    }
}