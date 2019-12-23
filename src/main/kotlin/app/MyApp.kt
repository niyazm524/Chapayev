package app

import ChapayevClient
import ru.itis.networking.GameServer
import tornadofx.*
import view.MenuView

class MyApp : App(MenuView::class, Styles::class) {
    lateinit var server: GameServer

    override fun stop() {
        super.stop()
        ChapayevClient.recycle()
        server.recycle()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch<MyApp>(args)
        }
    }

    override fun init() {
        super.init()
        server = GameServer()
        server.start()
    }
}