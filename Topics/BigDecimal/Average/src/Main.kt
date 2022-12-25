import java.math.BigDecimal
import java.math.RoundingMode

const val THREE = 3

fun main() {
    val a = BigDecimal(readln())
    val b = BigDecimal(readln())
    val c = BigDecimal(readln())
    print(((a + b + c) / THREE.toBigDecimal()).setScale(0, RoundingMode.DOWN))
}