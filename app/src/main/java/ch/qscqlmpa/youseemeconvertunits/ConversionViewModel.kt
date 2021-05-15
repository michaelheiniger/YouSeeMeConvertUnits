package ch.qscqlmpa.youseemeconvertunits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class ConversionViewModel : ViewModel() {

    private val oneMileInKm = "1.609344"

    private val milesInput = MutableStateFlow("1.0")
    private val kmInput = MutableStateFlow("1.0")
    private val _state = MutableStateFlow(ConversionViewState())

    val state: StateFlow<ConversionViewState> get() = _state

    init {
        viewModelScope.launch {
            combine(
                kmInput,
                milesInput
            ) { km, miles ->
                ConversionViewState(
                    km = km,
                    miles = miles,
                    milesInKm = convertMilesToKm(miles),
                    kmInMiles = convertKmToMiles(km).toString()
                )
            }.collect { state -> _state.value = state }
        }
    }

    fun onMilesChanged(miles: String) {
        milesInput.value = miles.formatAsDecimalNumber()
    }

    fun onKmChanged(km: String) {
        kmInput.value = km.formatAsDecimalNumber()
    }

    private fun convertMilesToKm(miles: String): String {
        return (BigDecimal(miles).multiply(BigDecimal(oneMileInKm)))
            .setScale(2, RoundingMode.HALF_EVEN)
            .toPlainString()
    }

    private fun convertKmToMiles(km: String): String {
        return BigDecimal(km).divide(BigDecimal(oneMileInKm), 2, RoundingMode.HALF_EVEN)
            .toPlainString()
    }
}

data class ConversionViewState(
    val km: String = "0.0",
    val miles: String = "0.0",
    val kmInMiles: String = "0.0",
    val milesInKm: String = "0.0"
)