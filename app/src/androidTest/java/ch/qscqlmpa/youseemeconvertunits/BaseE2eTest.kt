package ch.qscqlmpa.youseemeconvertunits

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule

abstract class BaseE2eTest {
    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()
}