package com.hieuwu.groceriesstore.data.repository

import com.hieuwu.groceriesstore.domain.models.MealModel

interface MealPlanRepository {
    suspend fun addMealToPlan(
        weekDay: String,
        name: String,
        ingredients: List<String>,
        mealType: String
    )

    suspend fun retrieveMealByType(type: String, weekDayValue: String): List<MealModel>
}