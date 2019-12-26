package server

import core.Room
import net.*

object ChapayevServer : GameServer(port = 3443) {
    val rooms = listOf(Room(1, "default"))
    val players = hashMapOf<String, Player>()

    override fun onPacket(packet: NetPacket, hasCallback: Boolean) {
        when (packet) {
            is LoginPacket -> onLogin(packet)
            is RequestRoomsPacket -> packet.replyWith(RoomsPacket(rooms))
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