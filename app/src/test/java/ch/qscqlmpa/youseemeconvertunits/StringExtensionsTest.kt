package ch.qscqlmpa.youseemeconvertunits

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class StringExtensionsTest {

    @Test
    fun `Remove all non digit characters (except dots)`() {
        assertThat("a3d.e1-f4{a1*5d".removeNonDigit()).isEqualTo("31415")
    }

    @Test
    fun `Remove all non-digit characters`() {
        assertThat("31{4#1)5ad9et$26".formatAsDecimalNumber()).isEqualTo("31415926.0")
    }

    @Test
    fun `Remove all non-digit characters except the first dot`() {
        assertThat("3.1{4#1)5ad9et$26".formatAsDecimalNumber()).isEqualTo("3.14")
    }

    @Test
    fun `format string as decimal number with 2 digits after comma`() {
        assertThat("3.1415926".formatAsDecimalNumber()).isEqualTo("3.14")
    }
}