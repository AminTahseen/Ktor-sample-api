package com.example.repository

import com.example.models.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import java.lang.Exception

class DatabaseFactory {
    private val client=KMongo.createClient().coroutine
    private val database=client.getDatabase("KMongo_DB")

    private val customerCollection:CoroutineCollection<Customer> = database.getCollection()
    private val foodCollection:CoroutineCollection<Food> =database.getCollection()
    private val orderCollection:CoroutineCollection<Order> =database.getCollection()

    suspend fun addCustomer(customer: Customer):Responses<Customer>{
       val status= customerCollection.insertOne(customer)
        return if (status.wasAcknowledged())
            Responses(status = "200", message = "Customer Stored Successfully", data = customer)
        else
            Responses(status = "404", message = "Customer Not Stored", data = null)
    }

    suspend fun getAllCustomer():Responses<List<Customer>>{
       val customers= customerCollection.find().toList()
        return if(customers.isNotEmpty())
            Responses(status = "200",message="Customer Found Successfully" ,data = customers)
        else
            Responses(status = "404",message="Customer Not Found" ,data = emptyList())
    }

    suspend fun addFood(food: Food):Responses<Food>{
        val status= foodCollection.insertOne(food)
        return if (status.wasAcknowledged())
            Responses(status = "200", message = "Food Stored Successfully", data = food)
        else
            Responses(status = "404", message = "Food Not Stored", data =null)
    }

    suspend fun getAllFood():Responses<List<Food>> {
        val foods=foodCollection.find().toList()
        return if(foods.isNotEmpty())
            Responses(status = "200",message="Foods Found Successfully" ,data = foods)
        else
            Responses(status = "404",message="Foods Not Found" ,data = emptyList())
    }
    suspend fun addOrder(order: Order):Responses<Order>{
        val status =orderCollection.insertOne(order)
        return if(status.wasAcknowledged())
            Responses(status = "200", message = "Order Stored Successfully", data = order)
        else
            Responses(status = "404", message = "Order Not Stored", data = null)
    }

    suspend fun getAllOrders():Responses<List<Order>> {
        val order=orderCollection.find().toList()
        return if(order.isNotEmpty()){
            Responses(status = "200",message="Orders Found Successfully" ,data = order)
        }else
            Responses(status = "404",message="Orders Not Found" ,data = emptyList())
    }

    suspend fun getCustomerOrder(customerId:String):Responses<TotalBill>{
        print("=====Inside getCustomerOrder=====\n")
        val foodItemList = mutableListOf<OrderedFoodItem>()
        var totalBill=0.0
        try {
            val order = orderCollection.findOne(Order::customerId eq customerId) as Order
            for (foodItem in order.food) {
                print("foodItem : $foodItem \n")
                val food = foodCollection.findOne(Food::id eq foodItem.foodId) as Food
                print("food : $food \n")
                val calculatedPrice = food.foodPrice.toDouble() * foodItem.quantity.toDouble()
                totalBill += calculatedPrice
                val orderFood = OrderedFoodItem(
                    food.foodName, "Rs. ${food.foodPrice}",
                    foodItem.quantity, "Rs. $calculatedPrice"
                )
                print("orderFood : $orderFood \n")
                foodItemList.add(orderFood)
            }
            val totalBillObj = TotalBill(foodItemList, "Rs. $totalBill")
            print("=====End getCustomerOrder=====")
            return Responses(status = "200",message="Order Found Successfully" ,data = totalBillObj)

        } catch (e: Exception) {
            return Responses<TotalBill>(status = "404", message = "Order Not Found" ,data = null)
        }
    }
}