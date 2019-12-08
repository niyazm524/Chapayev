package geometry

data class Rect(
        val left: Double,
        val top: Double,
        val right: Double,
        val bottom: Double
) {
    fun width() = right - left
    fun height() = bottom - top
}
