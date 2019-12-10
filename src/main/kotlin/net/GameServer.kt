package net

import java.net.DatagramPacket
import java.net.DatagramSocket

fun main() {
    GameServer().start()
}

class GameServer : UdpWorker() {
    override var socket = DatagramSocket(3443)

    override fun run() {
        while (true) {
            val data = ByteArray(1024)
            val p = DatagramPacket(data, data.size)
            socket.receive(p)
            val packet = PacketDecoder.fromBytes(p.data)
            val message = packet.toString()
            println("CLIENT -> $message")
            if (packet is LoginPacket) {
                println("player name is ${packet.playerName}")
                sendPacket(LoginPacket("hello ${packet.playerName}"), p.address, p.port)
            }
        }
    }
}
