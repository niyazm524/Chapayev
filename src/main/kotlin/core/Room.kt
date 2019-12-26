package core

data class Room(val id: Int, val name: String, var playersCount: Int = 0) {
    val state: String
        get() = when (playersCount) {
            0 -> "(пусто)"
            1 -> "(1 игрок)"
            2 -> "(фулл)"
            else -> "(неизвестно)"
        }

}