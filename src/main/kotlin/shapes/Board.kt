package shapes

import geometry.Rect
import javafx.scene.CacheHint
import javafx.scene.effect.DropShadow
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Rectangle

class Board(bounds: Rect) : Rectangle(bounds.left, bounds.top, bounds.width(), bounds.height()) {
    init {
        fill = ImagePattern(
                Image("/checkered.jpg"),
                x, y, 260.0, 260.0,
                false
        )
        effect = DropShadow(12.0, Color.GREY)
        isCache = true
        cacheHint = CacheHint.SPEED
    }
}