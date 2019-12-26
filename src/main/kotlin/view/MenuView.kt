package view

import ChapayevClient
import core.Room
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.Pane
import tornadofx.*
import java.net.InetAddress

class MenuView : View("Chapayev: Menu") {
    override val root: Pane by fxml("/view/MenuView.fxml")

    val roomsTableView: TableView<Room> by fxid()
    val roomsColumn: TableColumn<Room, String> by fxid()
    val stateColumn: TableColumn<Room, String> by fxid()

    fun onPlayClicked() = ChapayevClient.login("neyaz") { isSuccess ->
        if (isSuccess) runLater {
            replaceWith<GameView>(ViewTransition.Fade(1.seconds), sizeToScene = true, centerOnScreen = true)
        }
    }


    init {
        ChapayevClient.address = InetAddress.getLocalHost()
        roomsColumn.cellValueFactory = PropertyValueFactory<Room, String>("name")
        stateColumn.cellValueFactory = PropertyValueFactory<Room, String>("state")
        roomsTableView.isTableMenuButtonVisible = false
        ChapayevClient.requestRooms { rooms ->
            println(rooms)
            roomsTableView.items = rooms.asObservable()
        }
    }
}
