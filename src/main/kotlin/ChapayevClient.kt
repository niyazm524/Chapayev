import core.Room
import net.*
import tornadofx.*

object ChapayevClient : GameClient() {
    var isLogon = false
    fun login(username: String, callback: (isSuccess: Boolean) -> Unit) = LoginPacket(username).sendAwaiting {
        it as LogonPacket
        isLogon = it.success
        runLater { callback(it.success) }
    }

    fun requestRooms(callback: (rooms: List<Room>) -> Unit) = RequestRoomsPacket().sendAwaiting {
        it as RoomsPacket
        runLater { callback(it.rooms) }
    }

    override fun onPacket(packet: NetPacket, hasCallback: Boolean) {

    }
}