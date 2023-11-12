package com.example.xe.ui.xe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xe.domain.XeRepository
import com.example.xe.domain.model.Ad
import com.example.xe.domain.usecase.FilterDigits
import com.example.xe.domain.usecase.ValidateInputs
import com.example.xe.utils.DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class XeViewModel @Inject constructor(
    private val filterDigits: FilterDigits,
    private val repository: XeRepository,
    private val validateInputs: ValidateInputs
) : ViewModel(

) {

    var state by mutableStateOf<XeState>(XeState())
        private set

    fun onEvent(event: XeEvent) {
        when (event) {
            is XeEvent.OnChangeTitleText -> {
                state = state.copy(
                    title = event.text,
                    hasErrorTitleTextField = false
                )
            }

            is XeEvent.OnChangeLocationText -> {
                state = state.copy(
                    location = event.query,
                    isLoading = false,
                    isLocationValid = false,
                    hasErrorLocationTextField = false
                )

                job?.cancel()

                if(event.query.length >= 3){
                    searchLocations(event.query)
                }else{
                    state = state.copy(listFromApi = emptyList())
                }

            }

            is XeEvent.OnLocationItemClicked -> {
                state = state.copy(
                    location = "${event.searchDto.mainText}, ${event.searchDto.secondaryText}",
                    placeId = event.searchDto.placeId,
                    isLocationValid = true,
                    listFromApi = emptyList()
                )
            }
            is XeEvent.OnChangePriceText -> {
                state = state.copy(
                    price = filterDigits(event.text)
                )
            }

            is XeEvent.OnChangeDescriptionText -> {
                state = state.copy(
                    description = event.text
                )
            }

            is XeEvent.OnSubmitClicked -> {
                val result = validateInputs(
                    title = state.title,
                    location = state.location,
                    isLocationValid = state.isLocationValid
                )

                when(result){
                    ValidateInputs.Result.ErrorEmptyLocation -> {
                        state = state.copy(
                            errorLocationText = "Location should not be empty",
                            hasErrorLocationTextField = true
                        )
                    }
                    ValidateInputs.Result.ErrorEmptyTitle -> {
                        state = state.copy(
                            errorTitleText = "Title should not be empty",
                            hasErrorTitleTextField = true
                        )
                    }
                    ValidateInputs.Result.ErrorInvalidLocation -> {
                        state = state.copy(
                            errorLocationText = "Location is not valid",
                            hasErrorLocationTextField = true
                        )
                    }
                    ValidateInputs.Result.Success -> {
                        state = state.copy(
                            jsonAdString = convertAdToJson(),
                            isDialogShown = true,
                            isLocationValid = false
                        )
                        clearXeFields()
                    }
                }

            }

            XeEvent.OnClearClicked -> {
                clearXeFields()
            }
            XeEvent.OnDialogDismissClicked -> {
                state = state.copy(isDialogShown = false)

            }
        }
    }

    private var job : Job? = null
    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    private fun clearXeFields() {
        state = state.copy(
            title = "",
            location = "",
            description = "",
            price = "",
            placeId = "",
            hasErrorLocationTextField = false,
            hasErrorTitleTextField = false,
            isLocationValid = false,
            listFromApi = emptyList(),


            )
    }

    private fun searchLocations(query : String) {
        job = viewModelScope.launch {
            state = state.copy(isLoading = true)

            delay(DELAY)

            repository.searchProducts(query)
                .onSuccess {
                    state = state.copy(listFromApi = it, isLoading = false)

            }
                .onFailure {
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.ShowToast("Something went wrong"))
                    }
                    state = state.copy(isLoading = false)
                }
        }
    }
    private fun convertAdToJson() : String{

        return Json.encodeToString(
            Ad(
                title = state.title,
                location = state.location,
                placeId = state.placeId,
                price = state.price,
                description = state.description
            )
        )

    }

    sealed class UiEvent(){
        data class ShowToast(val message : String) : UiEvent()
    }




}