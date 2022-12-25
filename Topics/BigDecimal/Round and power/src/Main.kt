import java.math.BigDecimal
import java.math.RoundingMode     

fun main() {
    val power = readln().toInt()
    val mode = readln().toInt()
    val number = BigDecimal(readln())

    print(number.setScale(mode, RoundingMode.FLOOR).pow(power))
}