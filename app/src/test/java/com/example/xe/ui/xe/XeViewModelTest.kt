package com.example.xe.ui.xe

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import com.example.xe.domain.XeRepository
import com.example.xe.domain.usecase.FilterDigits
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class XeViewModelTest {
    lateinit var viewModel: XeViewModel
    lateinit var repository: XeRepository

    @BeforeEach
    fun setUp(){
        repository = XeRepositoryFake()
        viewModel = XeViewModel(
            filterDigits = FilterDigits(),
            repository = repository
        )
    }

    @Test
    fun `User enters title, state title is updated`(){
        val title = "Car Ad"
        viewModel.onEvent(XeEvent.OnChangeTitleText(title))
        println(viewModel.state)

        assertThat(viewModel.state.title).isNotEmpty()
        assertThat(viewModel.state.title).isEqualTo(title)
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