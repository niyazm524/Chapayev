package net

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

abstract class UdpWorker : Thread() {
    abstract var socket: DatagramSocket

    protected open fun sendPacket(packet: NetPacket, ipAddress: InetAddress, port: Int) {
        val bytes = packet.toBytes()
        socket.send(DatagramPacket(bytes, bytes.size, ipAddress, port))
    }
}