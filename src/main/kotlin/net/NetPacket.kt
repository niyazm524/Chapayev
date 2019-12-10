package net

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

enum class PacketType(val int: Int) {
    Empty(0), Login(1);

    companion object {
        private val map = PacketType.values().associateBy(PacketType::int)
        fun fromInt(type: Int): PacketType = map[type] ?: error("No such a packet type")
    }
}

sealed class NetPacket(var type: PacketType) {
    var id: Int = -1
    abstract fun DataOutputStream.writeToPacket()

    fun toBytes(): ByteArray {
        val bytesStream = ByteArrayOutputStream()
        DataOutputStream(bytesStream).use { dataStream ->
            dataStream.writeInt(type.int)
            dataStream.writeInt(id)
            dataStream.writeToPacket()
        }
        return bytesStream.toByteArray()
    }

    companion object
}

class EmptyPacket : NetPacket(PacketType.Empty) {
    override fun DataOutputStream.writeToPacket() {}
}

class LoginPacket(var playerName: String = "unknown") : NetPacket(PacketType.Login) {

    override fun DataOutputStream.writeToPacket() {
        writeUTF(playerName)
    }

}

