package com.bearminds.passkeep.views

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<E : BaseViewModel.ViewEvent, S : BaseViewModel.ViewState> : ViewModel() {

    interface ViewEvent
    interface ViewState

    protected abstract val initialState: S

    private val _state = MutableStateFlow<S?>(null)
    val state: StateFlow<S?> = _state.asStateFlow()

    init {
        setState { initialState }
    }

    abstract fun handleEvent(event: E)

    protected fun setState(update: S.() -> S) {
        _state.value = (_state.value ?: initialState).update()
    }
}
