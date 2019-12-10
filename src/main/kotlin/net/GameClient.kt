package net

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class GameClient(val game: NetGame, var ipAddress: InetAddress) : UdpWorker() {
    override var socket = DatagramSocket()
    val buffer = ByteArray(1024)


    override fun run() {
        while (true) {
            val p = DatagramPacket(buffer, buffer.size)
            socket.receive(p)
            val packet = PacketDecoder.fromBytes(p.data)
            when (packet) {
                is LoginPacket -> println("login: ${packet.playerName}")
            }
        }
    }

    fun sendPacket(packet: NetPacket) = sendPacket(packet, ipAddress, 3443)

    fun login(username: String) {
        sendPacket(LoginPacket(username))
    }

}

interface NetGame