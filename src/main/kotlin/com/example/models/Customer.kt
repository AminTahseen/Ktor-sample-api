package com.example.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Customer(
    @BsonId
    val id: String?=ObjectId().toString(),
    val firstName: String,
    val lastName: String,
    val email: String,
)

