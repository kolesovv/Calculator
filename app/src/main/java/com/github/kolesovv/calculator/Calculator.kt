package com.github.kolesovv.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Calculator(modifier: Modifier, viewModel: CalculatorViewModel = viewModel()) {

    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .weight(1.0f)
                .padding(bottom = 16.dp, start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when (val currentState = state.value) {
                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = Symbol.EMPTY.value,
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                CalculatorState.Initial -> {}

                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        fontSize = 36.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = currentState.result,
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        fontSize = 36.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = Symbol.EMPTY.value,
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AssistOptionsButton(
                modifier = Modifier
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.SQRT))
                    }, text = Symbol.SQRT.value
            )
            AssistOptionsButton(
                modifier = Modifier
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.PI))
                    }, text = Symbol.PI.value
            )
            AssistOptionsButton(
                modifier = Modifier
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.POWER))
                    }, text = Symbol.POWER.value
            )
            AssistOptionsButton(
                modifier = Modifier
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.FACTORIAL))
                    }, text = Symbol.FACTORIAL.value
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Clear)
                    }
                    .background(MaterialTheme.colorScheme.secondary),
                text = Symbol.CLEAR.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.PARENTHESIS))
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.PARENTHESIS.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.PERCENT))
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.PERCENT.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIVIDE))
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.DIVIDE.value
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_7))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_7.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_8))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_8.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_9))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_9.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.MULTIPLY))
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.MULTIPLY.value.uppercase()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_4))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_4.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_5))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_5.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_6))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_6.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.SUBTRACT))
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.SUBTRACT.value
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_1))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_1.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_2))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_2.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_3))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DIGIT_3.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.ADD))
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.ADD.value
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DIGIT_0))
                    }
                    .background(MaterialTheme.colorScheme.primary)
                    .aspectRatio(2 / 1f)
                    .weight(2.0f),
                text = Symbol.DIGIT_0.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Input(Symbol.DOT))
                    }
                    .background(MaterialTheme.colorScheme.primary),
                text = Symbol.DOT.value
            )
            MainOptionsButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.processUserInput(CalculatorCommand.Evaluate)
                    }
                    .background(MaterialTheme.colorScheme.tertiary),
                text = Symbol.EVALUATE.value
            )
        }
    }
}

@Composable
private fun RowScope.MainOptionsButton(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .weight(1.0f)
            .aspectRatio(1.0f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun RowScope.AssistOptionsButton(modifier: Modifier = Modifier, text: String) {
    Box(
        modifier = modifier
            .weight(1.0f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
