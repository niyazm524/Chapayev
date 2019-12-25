package server

import net.GameServer
import net.LoginPacket
import net.LogonPacket
import net.NetPacket

object ChapayevServer : GameServer(port = 3443) {
    val players = hashMapOf<String, Player>()
    override fun onPacket(packet: NetPacket, hasCallback: Boolean) {
        print("packet: $packet")
        when (packet) {
            is LoginPacket -> onLogin(packet)
        }
    }

    private fun onLogin(packet: LoginPacket) {
        if (packet.playerName in players)
            packet.replyWith(LogonPacket(false))
        else {
            players[packet.playerName] = Player()
            packet.replyWith(LogonPacket(true))
        }
    }

}

class Player