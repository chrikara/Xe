package com.example.xe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.espresso.Espresso
import com.example.xe.data.XeApi
import com.example.xe.data.XeRepositoryImpl
import com.example.xe.domain.model.SearchDto
import com.example.xe.domain.usecase.FilterDigits
import com.example.xe.domain.usecase.ValidateInputs
import com.example.xe.ui.xe.XeViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class XeE2E {


    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var xeViewModel: XeViewModel


    @Before
    fun setUp(){
        xeViewModel = XeViewModel(
            validateInputs = ValidateInputs(),
            filterDigits = FilterDigits(),
            repository = XeRepositoryImpl(object : XeApi {
                override suspend fun getData(query: String): List<SearchDto> {
                    return emptyList()
                }
            })
        )

    }

    @Test
    fun properlyValidatingInputs_showingDialogWithValidResults() {



        val title1 = "Car Ad"
        val title2 = "House Ad"
        val price1 = 1500.0
        val price2 = 25000.0
        val description1 = "This product will change your life!"
        val description2 = "This product will change your life even more!"
        val location1 = "Athens"
        val location2 = "Thessaloniki"



        composeRule.onNodeWithTag("Dialog").assertDoesNotExist()
        composeRule.onNodeWithTag("Title").performTextInput(title1)
        composeRule.onNodeWithText("Clear").performClick()

        composeRule.onNodeWithTag("Location").performTextInput(location1)
        composeRule.onNodeWithTag("Title").performTextInput(title1)
        composeRule.onNodeWithTag("Price").performTextInput(price1.toString())
        composeRule.onNodeWithTag("Description").performTextInput(description1)
        composeRule.onRoot().printToLog("COMPOSE TREE")
        composeRule.onAllNodesWithTag("LocationItem").onFirst().performClick()
        composeRule.onNodeWithText("Submit").performClick()
        composeRule.onNodeWithTag("Dialog").assertIsDisplayed()
        Espresso.pressBack()
        composeRule.onNodeWithTag("Dialog").assertDoesNotExist()
        composeRule.onNodeWithTag("Title").performTextInput(title2)
        composeRule.onNodeWithText("Clear").performClick()

        composeRule.onNodeWithTag("Location").performTextInput(location2)
        composeRule.onNodeWithTag("Title").performTextInput(title2)
        composeRule.onNodeWithTag("Price").performTextInput(price2.toString())
        composeRule.onNodeWithTag("Description").performTextInput(description2)
        composeRule.onAllNodesWithTag("LocationItem").onFirst().performClick()
        composeRule.onNodeWithText("Submit").performClick()
        composeRule.onNodeWithTag("Dialog").assertIsDisplayed()


    }
}