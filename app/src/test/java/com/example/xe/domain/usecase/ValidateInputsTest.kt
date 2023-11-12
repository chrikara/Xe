package com.example.xe.domain.usecase


import assertk.assertThat
import assertk.assertions.isDataClassEqualTo
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test



internal class ValidateInputsTest{



    @Test
    fun `Give string inputs, validate results` (

    ){
        val validateInput = ValidateInputs()
        val listOfValidateInputs = listOf<ValidateInputs.Result>(

            validateInput("Car Ad", "", false),
            validateInput("", "Αττική, Ελλάδαα", false),
            validateInput("", "", false),
            validateInput("House Ad", "Αττική, Ελλάδαα", false),
            validateInput("", "Αττική, Ελλάδαα", true),
            validateInput("House Ad", "Αττική, Ελλάδαα", true)
        )

        var count = 0

        assertThat(listOfValidateInputs[count++]).isEqualTo(ValidateInputs.Result.ErrorEmptyLocation)
        assertThat(listOfValidateInputs[count++]).isEqualTo(ValidateInputs.Result.ErrorEmptyTitle)
        assertThat(listOfValidateInputs[count++]).isEqualTo(ValidateInputs.Result.ErrorEmptyTitle)
        assertThat(listOfValidateInputs[count++]).isEqualTo(ValidateInputs.Result.ErrorInvalidLocation)
        assertThat(listOfValidateInputs[count++]).isEqualTo(ValidateInputs.Result.ErrorEmptyTitle)
        assertThat(listOfValidateInputs[count++]).isEqualTo(ValidateInputs.Result.Success)



    }
}