package com.example.xe.ui.xe

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEmpty
import assertk.assertions.isTrue
import com.example.xe.MainCoroutineExtension
import com.example.xe.domain.XeRepository
import com.example.xe.domain.model.SearchDto
import com.example.xe.domain.usecase.FilterDigits
import com.example.xe.domain.usecase.ValidateInputs
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


@ExtendWith(MainCoroutineExtension::class)

class XeViewModelTest {
    lateinit var viewModel: XeViewModel
    lateinit var repository: XeRepositoryFake
    var query = ""
    var price = ""
    var title = ""

    @BeforeEach
    fun setUp(){
        repository = XeRepositoryFake()
        viewModel = XeViewModel(
            filterDigits = FilterDigits(),
            validateInputs = ValidateInputs(),
            repository = repository
        )
        query = "Athens"
        title = "Car Ad"
        price = "500.0"
    }

    @Test
    fun `User enters title, state title is updated`() {
        viewModel.onEvent(XeEvent.OnChangeTitleText(title))
        println(viewModel.state)



        assertThat(viewModel.state.title).isNotEmpty()
        assertThat(viewModel.state.title).isEqualTo(title)
    }

    @Test
    fun `User enters a query with 3 letters, list from API is updated,user clicks LocationItem, location text is updated, location is valid`()
    = runTest{
        viewModel.onEvent(XeEvent.OnChangeLocationText(query))
        advanceUntilIdle()


        assertThat(viewModel.state.listFromApi).isNotEmpty()
        assertThat(viewModel.state.isLoading).isFalse()

        val itemClicked = repository.items[0]

        viewModel.onEvent(XeEvent.OnLocationItemClicked(itemClicked))

        assertThat(viewModel.state.listFromApi).isEmpty()
        assertThat(viewModel.state.location).isEqualTo("${itemClicked.mainText}, ${itemClicked.secondaryText}")
        assertThat(viewModel.state.isLocationValid).isTrue()
    }

    @Test
    fun `User types locationField after correctly clicking a valid location, location is now invalid`() = runTest{

        viewModel.onEvent(XeEvent.OnChangeLocationText(query))
        advanceUntilIdle()
        assertThat(viewModel.state.listFromApi).isNotEmpty()

        val itemClicked = repository.items[0]
        viewModel.onEvent(XeEvent.OnLocationItemClicked(itemClicked))
        assertThat(viewModel.state.isLocationValid).isTrue()

        viewModel.onEvent(XeEvent.OnChangeLocationText(query))
        assertThat(viewModel.state.isLocationValid).isFalse()
    }

    @Test
    fun `User clears all non-empty fields`(){

        viewModel.apply {
            onEvent(XeEvent.OnChangeTitleText(title))
            onEvent(XeEvent.OnChangeDescriptionText(query))
            onEvent(XeEvent.OnChangeLocationText(query))
            onEvent(XeEvent.OnChangePriceText(price))
        }

        assertThat(viewModel.state.title).isNotEmpty()
        assertThat(viewModel.state.description).isNotEmpty()
        assertThat(viewModel.state.price).isNotEmpty()
        assertThat(viewModel.state.location).isNotEmpty()

        viewModel.onEvent(XeEvent.OnClearClicked)

        assertThat(viewModel.state.title).isEmpty()
        assertThat(viewModel.state.description).isEmpty()
        assertThat(viewModel.state.price).isEmpty()
        assertThat(viewModel.state.location).isEmpty()
        assertThat(viewModel.state.hasErrorLocationTextField).isFalse()
        assertThat(viewModel.state.hasErrorTitleTextField).isFalse()
    }

    @Test
    fun `User clicks Submit with location valid, dialog shown`() {
        viewModel.apply {
            onEvent(XeEvent.OnChangeTitleText(query))
            onEvent(XeEvent.OnChangeDescriptionText(query))
            onEvent(XeEvent.OnChangeLocationText(query))
        }
        viewModel.onEvent(XeEvent.OnLocationItemClicked(repository.items[0]))
        viewModel.onEvent(XeEvent.OnSubmitClicked)

        assertThat(viewModel.state.isDialogShown).isTrue()
    }

    @Test
    fun `User clicks Submit with all fields but title is not given, dialog notshown`() {
        viewModel.apply {
            onEvent(XeEvent.OnChangeDescriptionText(query))
            onEvent(XeEvent.OnChangeLocationText(query))
        }
        viewModel.onEvent(XeEvent.OnLocationItemClicked(repository.items[0]))
        viewModel.onEvent(XeEvent.OnSubmitClicked)

        assertThat(viewModel.state.isDialogShown).isFalse()
        assertThat(viewModel.state.title).isEmpty()
        assertThat(viewModel.state.hasErrorTitleTextField).isTrue()
        assertThat(viewModel.state.errorTitleText).isNotEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        "5, 5",
        "5a, 5",
        "5., 5.",
        "5.2, 5.2",
        "5.2., 5.2",
        "5.25, 5.25",
        "0, 0",
        "0., 0.",
        "0.5, 0.5",
        "0.50, 0.50",
        "0.5., 0.5",
        "00, 0",
        "08, 0",
    )
    fun `User enters price, state price is correctly updated `(query: String, expectedResult: String){


        viewModel.onEvent(XeEvent.OnChangePriceText(query))

        assertThat(viewModel.state.price).isEqualTo(expectedResult)
    }









}