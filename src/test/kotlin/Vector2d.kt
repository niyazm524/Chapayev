import geometry.Vector2d
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class Vector2dTest : StringSpec({
    "сумма векторов" {
        Vector2d(1.0, 2.0) + Vector2d(80.0, -90.0) shouldBe Vector2d(81.0, -88.0)
    }

    "умножение вектора на число" {
        Vector2d(3.0, -1.0) * 3.0 shouldBe Vector2d(9.0, -3.0)
    }

    "нормализация вектора" {
        Vector2d(8.0, 4.0).normalize() shouldBe Vector2d(0.8944271909999159, 0.4472135954999579)
    }

    "перемножение векторов" {
        Vector2d(2.0, 2.0).dot(Vector2d(4.0, 3.0)) shouldBe 14.0
    }
})