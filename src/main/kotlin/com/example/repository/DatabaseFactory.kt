package com.example.repository

import com.example.models.Customer
import com.example.models.Food
import com.example.models.Order
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseFactory {
    private val client=KMongo.createClient().coroutine
    private val database=client.getDatabase("KMongo_DB")

    private val customerCollection:CoroutineCollection<Customer> = database.getCollection()
    private val foodCollection:CoroutineCollection<Food> =database.getCollection()
    private val orderCollection:CoroutineCollection<Order> =database.getCollection()

    suspend fun addCustomer(customer: Customer):Customer{
        customerCollection.insertOne(customer)
        return customer
    }

    suspend fun getAllCustomer():List<Customer> =customerCollection.find().toList()

    suspend fun addFood(food: Food):Food{
        foodCollection.insertOne(food)
        return food
    }

    suspend fun getAllFood():List<Food> =foodCollection.find().toList()

    suspend fun addOrder(order: Order):Order{
        orderCollection.insertOne(order)
        return order
    }

    suspend fun getAllOrders():List<Order> =orderCollection.find().toList()
}