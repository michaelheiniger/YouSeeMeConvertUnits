package ch.qscqlmpa.youseemeconvertunits

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversionTest : BaseE2eTest() {

    @Test
    fun testMilesToKilometersConversion() {
        // When
        testRule.onNodeWithTag(UiTags.MilesInput.tag).performTextReplacement("2.0")

        // Then
        testRule.onNodeWithTag(UiTags.MilesInKm.tag)
            .assertIsDisplayed()
            .assertTextContains("3.22")

        testRule.onNodeWithTag(UiTags.KmInMiles.tag)
            .assertIsDisplayed()
            .assertTextContains("0.62")
    }

    @Test
    fun testKilometersToMilesConversion() {
        // When
        testRule.onNodeWithTag(UiTags.KmInput.tag).performTextReplacement("2.0")

        // Then
        testRule.onNodeWithTag(UiTags.MilesInKm.tag)
            .assertIsDisplayed()
            .assertTextContains("1.61")

        testRule.onNodeWithTag(UiTags.KmInMiles.tag)
            .assertIsDisplayed()
            .assertTextContains("1.24")
    }
}