package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class TotalBill(
    val orderedFoodItem: List<OrderedFoodItem>,
    val totalBill: String
)