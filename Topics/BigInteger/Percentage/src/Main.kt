fun main() {
    val hundred = 100.toBigInteger()
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    val sum = a + b

    print("${hundred * a / sum}% ${hundred * b / sum}%")
}