import java.math.BigDecimal
import java.math.RoundingMode

const val TWO = 2
const val HUNDRED = 100

fun main() {
    val startingAmount = BigDecimal(readln())
    val interestRate = readln()
    val numberOfYears = readln().toInt()

    fun convertInterestRate(interestRate: String): BigDecimal {
        return if (interestRate.contains(".")) {
            interestRate.toBigDecimal()
                .setScale(interestRate.substringAfter(".").length + TWO, RoundingMode.UNNECESSARY) / HUNDRED.toBigDecimal()
        } else {
            interestRate.toBigDecimal()
                .setScale(interestRate.length + TWO, RoundingMode.UNNECESSARY) / HUNDRED.toBigDecimal()
        }
    }

    val finalAmount = startingAmount * (BigDecimal.ONE + convertInterestRate(interestRate)).pow(numberOfYears)

    print("Amount of money in the account: ${finalAmount.setScale(TWO, RoundingMode.CEILING)}")
}