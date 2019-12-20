package net

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.SocketException

fun main() {
    GameServer().start()
}

open class GameServer : UdpWorker() {
    override var socket = DatagramSocket(3443)

    override fun run() {
        while (!isInterrupted) {
            try {
                val data = ByteArray(1024)
                val datagramPacket = DatagramPacket(data, data.size)
                socket.receive(datagramPacket)
                onDatagramReceived(datagramPacket)
            } catch (e: SocketException) {
                if (!isInterrupted) System.err.println("Client: ${e.message}")
            } catch (e: IOException) {
                e.printStackTrace(System.err)
            }
        }
    }

    override fun onPacket(packet: NetPacket, hasCallback: Boolean) {
        print("Packet: ${packet.id}")
    }
}
