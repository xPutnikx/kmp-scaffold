@file:OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)

package com.bearminds.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<
    Event : BaseViewModel.ViewEvent,
    UIState : BaseViewModel.ViewState
    > : ViewModel() {

    protected abstract val initialState: UIState

    protected abstract fun handleEvent(event: Event)

    private val _viewState: MutableStateFlow<UIState> by lazy { MutableStateFlow(initialState) }

    open val viewState: StateFlow<UIState> by lazy { _viewState.asStateFlow() }

    private val _event: Channel<Event> by lazy { Channel() }
    private val _effect: Channel<ViewEffect> by lazy { Channel() }

    val effect: Flow<ViewEffect> = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() = viewModelScope.launch {
        _event.receiveAsFlow().conflate().collect {
            handleEvent(it)
        }
    }

    fun onEvent(event: Event) = viewModelScope.launch {
        _event.send(event)
    }

    protected fun setState(reducer: UIState.() -> UIState) {
        _viewState.tryEmit(reducer(_viewState.value))
    }

    protected fun setEffect(builder: () -> ViewEffect) {
        val effect = builder()
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    interface ViewEvent

    interface ViewState

    interface ViewEffect

    data class SnackbarEffect(val message: String) : ViewEffect
    data class HapticFeedbackEffect(val type: HapticType) : ViewEffect
    data object NavigateBackEffect : ViewEffect

    enum class HapticType {
        LIGHT,
        MEDIUM
    }
}
