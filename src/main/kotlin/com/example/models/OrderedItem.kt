package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderedItem (
    val foodId:String,
    val quantity:String,
)
