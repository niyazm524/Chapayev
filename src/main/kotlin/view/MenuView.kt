package view

import ChapayevClient
import javafx.scene.layout.Pane
import tornadofx.*
import java.net.InetAddress

class MenuView : View("Chapayev: Menu") {
    override val root: Pane by fxml("/view/MenuView.fxml")

    fun onPlayClicked() = ChapayevClient.login("neyaz") { isSuccess ->
        if (isSuccess) runLater {
            replaceWith<GameView>(ViewTransition.Fade(1.seconds), sizeToScene = true, centerOnScreen = true)
        }
    }


    init {
        ChapayevClient.address = InetAddress.getLocalHost()
//        runLater(2.seconds) {
//            replaceWith<GameView>(ViewTransition.Fade(1.seconds), sizeToScene = true, centerOnScreen = true)
//        }
    }
}
