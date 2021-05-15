package ch.qscqlmpa.youseemeconvertunits

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ConversionScreen() {
    val viewModel = viewModel(ConversionViewModel::class.java)
    val viewState by viewModel.state.collectAsState()

    Surface(Modifier.fillMaxSize()) {
        DistanceConversion(
            viewState,
            onMilesChange = viewModel::onMilesChanged,
            onKmChange = viewModel::onKmChanged
        )
    }
}

@Composable
fun DistanceConversion(
    state: ConversionViewState,
    onMilesChange: (String) -> Unit,
    onKmChange: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Conversion(
            srcUnitValue = state.miles,
            dstUnitValue = state.milesInKm,
            srcUnitLabel = R.string.miles,
            dstUnitLabel = R.string.kilometers_result,
            inputTestTag = UiTags.MilesInput,
            outputTestTag = UiTags.MilesInKm,
            onSrcValueChange = onMilesChange,
        )
        Spacer(Modifier.height(16.dp))
        Conversion(
            srcUnitValue = state.km,
            dstUnitValue = state.kmInMiles,
            srcUnitLabel = R.string.kilometers_long,
            dstUnitLabel = R.string.miles_result,
            inputTestTag = UiTags.KmInput,
            outputTestTag = UiTags.KmInMiles,
            onSrcValueChange = onKmChange,
        )
    }
}

@Composable
private fun Conversion(
    srcUnitValue: String,
    dstUnitValue: String,
    srcUnitLabel: Int,
    dstUnitLabel: Int,
    inputTestTag: UiTags,
    outputTestTag: UiTags,
    onSrcValueChange: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        OutlinedTextField(
            value = srcUnitValue,
            label = { Text(stringResource(srcUnitLabel)) },
            onValueChange = onSrcValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.testTag(inputTestTag.tag)
                .weight(1f)
        )
        Text(
            text = stringResource(dstUnitLabel, dstUnitValue),
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag(outputTestTag.tag)
                .align(Alignment.CenterVertically)
                .weight(1f)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun DistanceConversionPreview() {
    DistanceConversion(
        ConversionViewState(
            km = "1.0",
            miles = "1.0",
            kmInMiles = "0.621",
            milesInKm = "1.609"
        ),
        onMilesChange = {},
        onKmChange = {}
    )
}
