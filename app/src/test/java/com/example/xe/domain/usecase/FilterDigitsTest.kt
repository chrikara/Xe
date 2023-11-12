package com.example.xe.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class FilterDigitsTest {


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
    fun `Give price, return correct format with custom filtering`(numberString: String, expectedResult: String){

        val filterDigits = FilterDigits()

        val formattedString = filterDigits(numberString)

        assertThat(formattedString).isEqualTo(expectedResult)
    }


}