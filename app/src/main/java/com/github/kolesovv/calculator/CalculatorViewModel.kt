package com.github.kolesovv.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mariuszgromada.math.mxparser.Expression

class CalculatorViewModel : ViewModel() {

    private val _state: MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState.Initial
    )
    val state = _state.asStateFlow()
    private var expression = Symbol.EMPTY.value

    fun processUserInput(command: CalculatorCommand) {
        Log.d(TAG, "Command: $command")
        when (command) {
            CalculatorCommand.Clear -> {
                expression = Symbol.EMPTY.value
                _state.value = CalculatorState.Initial
            }

            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                _state.value = if (result != null) {
                    expression = result
                    CalculatorState.Success(result = result)
                } else {
                    CalculatorState.Error(expression = expression)
                }
            }

            is CalculatorCommand.Input -> {
                expression += if (command.symbol == Symbol.PARENTHESIS) {
                    getCorrectParenthesis()
                } else {
                    command.symbol.value
                }

                _state.value = CalculatorState.Input(
                    expression = expression,
                    result = evaluate() ?: Symbol.EMPTY.value
                )
            }
        }
    }

    private fun evaluate(): String? {
        return expression
            .replace(Symbol.MULTIPLY.value.single(), '*')
            .replace(Symbol.DOT.value.single(), '.')
            .let { Expression(it) }
            .calculate()
            .takeIf { it.isFinite() }?.toString()
    }

    private fun getCorrectParenthesis(): String {
        var openCount = expression.count { it == '(' }
        var closeCount = expression.count { it == ')' }
        return when {
            expression.isEmpty() -> "("
            expression.last()
                .let { !it.isDigit() && it != ')' && it != Symbol.PI.value.single() } -> "("

            openCount > closeCount -> ")"
            else -> "("
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

sealed interface CalculatorState {

    data object Initial : CalculatorState
    data class Input(val expression: String, val result: String) : CalculatorState
    data class Success(val result: String) : CalculatorState
    data class Error(val expression: String) : CalculatorState
}
