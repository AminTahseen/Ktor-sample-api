package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Food(val id:String,val foodName:String, val foodPrice:String)

val foodStorage = mutableListOf<Food>()
