package net

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException

class GameClient(val game: NetGame, var ipAddress: InetAddress) : UdpWorker() {
    override var socket = DatagramSocket()
    val buffer = ByteArray(1024)


    override fun run() {
        while (!isInterrupted) {
            try {
                val p = DatagramPacket(buffer, buffer.size)
                socket.receive(p)
                val packet = PacketDecoder.fromBytes(p.data)
                when (packet) {
                    is LoginPacket -> println("login: ${packet.playerName}")
                }
            } catch (e: SocketException) {
                if (!isInterrupted) System.err.println("Client: ${e.message}")
            } catch (e: IOException) {
                e.printStackTrace(System.err)
            }
        }
    }

    fun sendPacket(packet: NetPacket) = sendPacket(packet, ipAddress, 3443)

    fun login(username: String) {
        sendPacket(LoginPacket(username))
    }

    fun recycle() {
        interrupt()
        socket.close()
    }
}

interface NetGame