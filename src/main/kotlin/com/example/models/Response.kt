package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Responses<out T : Any> (
    val status:String,
    val message:String,
    val data:T?,
)