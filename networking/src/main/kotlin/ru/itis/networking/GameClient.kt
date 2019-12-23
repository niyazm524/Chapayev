package ru.itis.networking

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException

abstract class GameClient : UdpWorker() {
    override var socket = DatagramSocket()
    var address: InetAddress? = null
    private val buffer = ByteArray(1024)

    override fun run() {
        while (!isInterrupted) {
            try {
                val datagramPacket = DatagramPacket(buffer, buffer.size)
                socket.receive(datagramPacket)
                onDatagramReceived(datagramPacket)
            } catch (e: SocketException) {
                if (!isInterrupted) System.err.println("Client: ${e.message}")
            } catch (e: IOException) {
                e.printStackTrace(System.err)
            }
        }
    }

    fun NetPacket.send() = address?.let { sendPacket(this, it, 3443) }

    fun NetPacket.sendAwaiting(callback: PacketCallback) =
            address?.let { sendAwaitingPacket(this, it, 3443, callback) }

}
