package ch.qscqlmpa.youseemeconvertunits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import ch.qscqlmpa.youseemeconvertunits.ui.theme.YouSeeMeConvertUnitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YouSeeMeConvertUnitsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ConversionScreen()
                }
            }
        }
    }
}