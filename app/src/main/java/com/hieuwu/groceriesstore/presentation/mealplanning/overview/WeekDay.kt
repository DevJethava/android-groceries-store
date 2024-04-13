package com.hieuwu.groceriesstore.presentation.mealplanning.overview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WeekDay(
    val name: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false),
)