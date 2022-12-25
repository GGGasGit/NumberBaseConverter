fun main() {
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()

    val (q, r) = a.divideAndRemainder(b)

    print("$a = $b * $q + $r")

}