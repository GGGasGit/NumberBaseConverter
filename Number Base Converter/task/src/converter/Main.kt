package converter

import java.math.BigInteger
import java.math.BigDecimal
import java.math.RoundingMode

// Do not delete this line

fun digitConverter(digit: Int): String {
    return when (digit) {
        in 10..36 -> (digit + 55).toChar().toString()
        else -> digit.toString()
    }
}

fun reverseDigitConverter(digit: Char): Int {
    return when (digit) {
        in 'A'..'Z' -> (digit.code - 55)
        else -> digit.digitToInt()
    }
}

fun convertIntegerFromDecimal(decimalNumber: BigInteger, targetBase: BigInteger): String {
    var result = ""
    var number = decimalNumber
    while (number >= targetBase) {
        result += digitConverter((number % targetBase).toInt())
        number /= targetBase
    }
    result += digitConverter(number.toInt())
    return result.reversed()
}

fun convertIntegerToDecimal(sourceNumber: String, sourceBase: String): String {
    var result = BigInteger.ZERO
    for (i in 0..sourceNumber.lastIndex) {
        result += reverseDigitConverter(sourceNumber.reversed()[i].uppercaseChar()).toBigInteger() * sourceBase.toBigInteger().pow(i)
    }
    return result.toString()
}

fun convertFloatingFromDecimal(decimalNumber: String, targetBase: String): String {
    val (inputIntPart, inputFractPart) = decimalNumber.split(".")
    val intPartConverted = convertIntegerFromDecimal(inputIntPart.toBigInteger(), targetBase.toBigInteger())

    var fractPartConverted = ""
    val base = targetBase.toBigDecimal()
    var number = BigDecimal("0.$inputFractPart")
    var intPart: BigDecimal
    var i = 0
    do {
        number *= base
        intPart = number.setScale(0, RoundingMode.DOWN)
        fractPartConverted += digitConverter(intPart.toInt())
        number -= intPart
        i++
    } while (number != BigDecimal.ZERO && i != 5)
    if (fractPartConverted.length < 5) {
        repeat(fractPartConverted.length - 5) {
            fractPartConverted += "0"
        }
    }
    return "$intPartConverted.$fractPartConverted"
}

fun convertFloatingToDecimal(sourceNumber: String, sourceBase: String): String {
    val (intPart, fractPart) = sourceNumber.split(".")
    var result = BigDecimal.ZERO
    for (i in 0..intPart.lastIndex) {
        result += reverseDigitConverter(intPart.reversed()[i].uppercaseChar()).toBigDecimal() * sourceBase.toBigDecimal().pow(i)
    }
    for (i in 0..fractPart.lastIndex) {
        result += reverseDigitConverter(fractPart[i].uppercaseChar()).toBigDecimal().setScale(10, RoundingMode.UP) / sourceBase.toBigDecimal().pow(i+1)
    }
    return result.toString()
}

fun convertFloatingBaseToBase(number: String, sourceBase: String, targetBase: String): String {
    val decimalNumber = convertFloatingToDecimal(number, sourceBase).toBigDecimal().setScale(7, RoundingMode.UP).toString()
    return convertFloatingFromDecimal(decimalNumber, targetBase)
}

fun main() {
    while (true) {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val input = readln()
        if (input != "/exit" && !Regex("\\d{1,2} \\d{1,2}").matches(input)) {
            println("Invalid input\n")
        } else {
            when (input) {
                "/exit" -> break
                else -> {
                    val (sourceBase, targetBase) = input.split(" ")
                    while (true) {
                        if (sourceBase.toInt() !in 2..36 || targetBase.toInt() !in 2..36) {
                            println("Source base and target base should be between 2 and 36, inclusive.\n")
                            break
                        }
                        print("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back) ")
                        when (val number = readln()) {
                            "/back" -> {
                                println("")
                                break
                            }
                            else -> {
                                if (number.contains('.')) {
                                    if (sourceBase == "10") {
                                        println("Conversion result: ${convertFloatingFromDecimal(number, targetBase)}\n")
                                    } else if (targetBase == "10") {
                                        println("Conversion result: ${BigDecimal(convertFloatingToDecimal(number, sourceBase)).setScale(5, RoundingMode.UP)}\n")
                                    } else {
                                        println("Conversion result: ${convertFloatingBaseToBase(number, sourceBase, targetBase)}\n")
                                    }
                                } else {
                                    if (sourceBase == "10") {
                                        println("Conversion result: ${convertIntegerFromDecimal(number.toBigInteger(), targetBase.toBigInteger())}\n")
                                    } else if (targetBase == "10") {
                                        println("Conversion result: ${convertIntegerToDecimal(number, sourceBase)}\n")
                                    } else {
                                        val decimalNumber = convertIntegerToDecimal(number, sourceBase)
                                        println("Conversion result: ${convertIntegerFromDecimal(decimalNumber.toBigInteger(), targetBase.toBigInteger())}\n")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}