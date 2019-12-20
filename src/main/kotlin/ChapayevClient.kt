import net.GameClient
import net.LoginPacket
import net.LogonPacket
import net.NetPacket

object ChapayevClient : GameClient() {
    fun login(username: String, callback: (isSuccess: Boolean) -> Unit) = LoginPacket(username).sendAwaiting {
        it as LogonPacket
        callback(it.success)
    }

    override fun onPacket(packet: NetPacket, hasCallback: Boolean) {

    }
}