import java.math.BigDecimal
import java.math.RoundingMode

fun main() {             
    val number = BigDecimal(readln())
    val newScale = readln().toInt()
    print(number.setScale(newScale, RoundingMode.HALF_DOWN))
}