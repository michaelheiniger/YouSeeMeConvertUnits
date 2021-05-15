package ch.qscqlmpa.youseemeconvertunits

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ConversionViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: ConversionViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ConversionViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `initial state`(): Unit = runBlockingTest {
        // Given
        val initialState = viewModel.state.value

        // Then
        assertThat(initialState.km).isEqualTo("1.0")
        assertThat(initialState.miles).isEqualTo("1.0")
        assertThat(initialState.milesInKm).isEqualTo("1.61")
        assertThat(initialState.kmInMiles).isEqualTo("0.62")
    }

    @Test
    fun `when miles changes, the state is updated accordingly`(): Unit = runBlockingTest {
        // When
        viewModel.onMilesChanged("2.0")

        // Then
        val state = viewModel.state.value
        assertThat(state.km).isEqualTo("1.0")
        assertThat(state.miles).isEqualTo("2.0")
        assertThat(state.milesInKm).isEqualTo("3.22")
        assertThat(state.kmInMiles).isEqualTo("0.62")
    }

    @Test
    fun `when km changes, the state is updated accordingly`(): Unit = runBlockingTest {
        // When
        viewModel.onKmChanged("2.0")

        // Then
        val state = viewModel.state.value
        assertThat(state.km).isEqualTo("2.0")
        assertThat(state.miles).isEqualTo("1.0")
        assertThat(state.milesInKm).isEqualTo("1.61")
        assertThat(state.kmInMiles).isEqualTo("1.24")
    }
}