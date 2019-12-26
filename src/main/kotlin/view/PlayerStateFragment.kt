package view

import javafx.scene.layout.VBox
import tornadofx.*

class PlayerStateFragment : Fragment("My View") {
    override val root: VBox by fxml("/view/PlayerState.fxml")
}
