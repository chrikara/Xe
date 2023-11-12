package com.example.xe.ui.xe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xe.domain.XeRepository
import com.example.xe.domain.usecase.FilterDigits
import com.example.xe.utils.DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class XeViewModel @Inject constructor(
    private val filterDigits: FilterDigits,
    private val repository: XeRepository
) : ViewModel(

) {

    var state by mutableStateOf<XeState>(XeState())
        private set

    private var job : Job? = null

    fun onEvent(event: XeEvent) {
        when (event) {
            is XeEvent.OnChangeTitleText -> {
                state = state.copy(
                    title = event.text
                )
            }

            is XeEvent.OnChangeLocationText -> {
                state = state.copy(
                    location = event.query,
                    isLoading = false
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

            XeEvent.OnClearClicked -> TODO()
            XeEvent.OnDialogDismissClicked -> TODO()
            is XeEvent.OnSubmitClicked -> TODO()
        }
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
                    println(it)
                    state = state.copy(isLoading = false)
                }
        }
    }









}