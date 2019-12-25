package net

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.net.InetAddress

enum class PacketType(val int: Int) {
    Empty(0), Login(1), Logon(2);

    companion object {
        private val map = PacketType.values().associateBy(PacketType::int)
        fun fromInt(type: Int): PacketType = map[type] ?: error("No such a packet type")
    }
}

sealed class NetPacket(var type: PacketType) {
    var id: Int = 0
    var replyingTo: Int = 0
    var source: Pair<InetAddress, Int>? = null
    abstract fun DataOutputStream.writeToPacket()

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

class EmptyPacket : NetPacket(PacketType.Empty) {
    override fun DataOutputStream.writeToPacket() {}
}

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

