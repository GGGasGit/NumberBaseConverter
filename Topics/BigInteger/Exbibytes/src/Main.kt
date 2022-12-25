import java.math.BigInteger

fun main() {
    val exbibytes = readln().toBigInteger()
    val oneExbibyteInBits = BigInteger("9223372036854775808")
    print("${exbibytes * oneExbibyteInBits}")
}