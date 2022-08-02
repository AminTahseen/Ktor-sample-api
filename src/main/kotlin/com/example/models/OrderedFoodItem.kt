package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderedFoodItem (
    val foodName:String,
    val price:String,
    val quantity:String,
    val calculatedPrice:String,
)