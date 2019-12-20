package net

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

typealias PacketCallback = (reply: NetPacket) -> Unit

abstract class UdpWorker : Thread() {
    abstract var socket: DatagramSocket
    private var lastPacketId = 0
    private val awaitingPacketsCallbacks = hashMapOf<Int, PacketCallback>()

    protected open fun sendPacket(packet: NetPacket, ipAddress: InetAddress, port: Int) {
        packet.id = ++lastPacketId
        val bytes = packet.toBytes()
        socket.send(DatagramPacket(bytes, bytes.size, ipAddress, port))
    }

    fun sendAwaitingPacket(packet: NetPacket, ipAddress: InetAddress, port: Int, callback: PacketCallback) {
        awaitingPacketsCallbacks[packet.id] = callback
        sendPacket(packet, ipAddress, port)
    }

    protected fun onDatagramReceived(datagramPacket: DatagramPacket) {
        val packet: NetPacket = PacketDecoder.fromBytes(datagramPacket.data)
        var hasCallback = false
        awaitingPacketsCallbacks.remove(packet.replyingTo)?.also { callback ->
            hasCallback = true
            callback(packet)
        }
        onPacket(packet, hasCallback)
    }

    abstract fun onPacket(packet: NetPacket, hasCallback: Boolean)

    open fun recycle() {
        interrupt()
        socket.close()
    }
}