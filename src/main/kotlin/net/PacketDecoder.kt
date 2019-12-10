package net

import java.io.ByteArrayInputStream
import java.io.DataInputStream

object PacketDecoder {
    fun fromBytes(bytes: ByteArray): NetPacket = DataInputStream(ByteArrayInputStream(bytes)).use { dataStream ->
        val type: PacketType = PacketType.fromInt(dataStream.readInt())
        val id = dataStream.readInt()
        return@use decode(type, dataStream).also {
            it.type = type
            it.id = id
        }
    }


    private fun decode(type: PacketType, dataStream: DataInputStream): NetPacket = when (type) {
        PacketType.Empty -> EmptyPacket()
        PacketType.Login -> LoginPacket().apply { playerName = dataStream.readUTF() }
    }
}