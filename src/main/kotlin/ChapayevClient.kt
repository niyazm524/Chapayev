import ru.itis.networking.GameClient
import ru.itis.networking.LoginPacket
import ru.itis.networking.LogonPacket
import ru.itis.networking.NetPacket

object ChapayevClient : GameClient() {
    fun login(username: String, callback: (isSuccess: Boolean) -> Unit) = LoginPacket(username).sendAwaiting {
        it as LogonPacket
        callback(it.success)
    }

    override fun onPacket(packet: NetPacket, hasCallback: Boolean) {

    }
}