import java.math.BigInteger

fun main() {
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()

    val lcm = if (a == BigInteger.ZERO || b == BigInteger.ZERO) {
        BigInteger.ZERO
    } else {
        a.divide(a.gcd(b)).multiply(b).abs()
    }
    print(lcm)
}