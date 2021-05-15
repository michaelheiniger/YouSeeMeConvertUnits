package ch.qscqlmpa.youseemeconvertunits

private class StringExtensions {
    // In order to find tests
}

fun String.removeNonDigit(): String {
    return replace("[^0-9]".toRegex(), "")
}

fun String.formatAsDecimalNumber(): String {
    val indexFirstDot = this.indexOfFirst { c -> c == '.' }
    val onlyDigits = this.removeNonDigit()
    return if (indexFirstDot == -1) {
        "$onlyDigits.0"
    } else {
        "${onlyDigits.substring(0 until indexFirstDot)}.${
            onlyDigits.substring(indexFirstDot).take(2)
        }"
    }
}
