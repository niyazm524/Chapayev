package net

import core.Room
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.net.InetAddress

var lastTypeId = 0

enum class PacketType {
    Empty, Login, Logon,
    RequestRooms, Rooms;

    val int: Int = lastTypeId++

    companion object {
        private val map = PacketType.values().associateBy(PacketType::int)
        fun fromInt(type: Int): PacketType = map[type] ?: error("No such a packet type")
    }
}

sealed class NetPacket(var type: PacketType) {
    var id: Int = 0
    var replyingTo: Int = 0
    var source: Pair<InetAddress, Int>? = null
    open fun DataOutputStream.writeToPacket() {}

    fun toBytes(): ByteArray {
        val bytesStream = ByteArrayOutputStream()
        DataOutputStream(bytesStream).use { dataStream ->
            dataStream.writeInt(type.int)
            dataStream.writeInt(id)
            dataStream.writeInt(replyingTo)
            dataStream.writeToPacket()
        }
        return bytesStream.toByteArray()
    }
}

class EmptyPacket : NetPacket(PacketType.Empty)

class LoginPacket(var playerName: String = "unknown") : NetPacket(PacketType.Login) {
    override fun DataOutputStream.writeToPacket() {
        writeUTF(playerName)
    }
}

class LogonPacket(var success: Boolean) : NetPacket(PacketType.Logon) {
    override fun DataOutputStream.writeToPacket() {
        writeBoolean(success)
    }
}

class RequestRoomsPacket : NetPacket(PacketType.RequestRooms)

class RoomsPacket(var rooms: List<Room>) : NetPacket(PacketType.Rooms) {
    override fun DataOutputStream.writeToPacket() {
        writeInt(rooms.size)
        for (room in rooms) {
            writeInt(room.id)
            writeUTF(room.name)
            writeInt(room.playersCount)
        }
    }
}