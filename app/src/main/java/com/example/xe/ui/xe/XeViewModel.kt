package com.example.xe.ui.xe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xe.domain.XeRepository
import com.example.xe.domain.usecase.FilterDigits
import com.example.xe.domain.usecase.ValidateInputs
import com.example.xe.utils.DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
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

    private var job : Job? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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

            XeEvent.OnClearClicked -> {
                clearXeFields()
            }
            XeEvent.OnDialogDismissClicked -> TODO()
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
                            errorLocationText = "Title should not be empty",
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
                            isDialogShown = true
                        )
                    }
                }

            }


            is XeEvent.OnLocationItemClicked -> {
                state = state.copy(
                    location = "${event.searchDto.mainText}, ${event.searchDto.secondaryText}",
                    isLocationValid = true,
                    listFromApi = emptyList()
                )
            }
        }
    }

    private fun clearXeFields() {
        state = state.copy(
            title = "",
            location = "",
            description = "",
            price = "",
            placeId = "",
            hasErrorLocationTextField = false,
            hasErrorTitleTextField = false,
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

    sealed class UiEvent(){
        data class ShowToast(val message : String) : UiEvent()
    }


}