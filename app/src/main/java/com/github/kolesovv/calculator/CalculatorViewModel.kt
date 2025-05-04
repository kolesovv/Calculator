package com.github.kolesovv.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        Display(
            expression = "45x8",
            result = "360"
        )
    )
    val state = _state.asStateFlow()

    fun processUserInput(command: CalculatorCommand) {
        Log.d(TAG, "Command: $command")
        when (command) {
            CalculatorCommand.Clear -> _state.value = Display("", "")
            CalculatorCommand.Evaluate -> {}
            is CalculatorCommand.Input -> {}
        }
    }

    companion object {
        const val TAG = "CalculatorViewModel"
    }
}

sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand
}

data class Display(var expression: String, var result: String)
