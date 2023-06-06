package com.example.porter01.repository

import com.example.porter01.model.PorterVehicle
import com.example.porter01.helpers.database.DatabaseHelper
import com.example.porter01.model.Order
import com.example.porter01.model.User

class Repository(private val databaseHelper : DatabaseHelper) :PorterVehicleRepository,OrdersRepository,UserRepository {
    override fun insertUser(user: User) {
        databaseHelper.insertUser(user)
    }

    override fun getUser(username: String, password: String): User? {
        return databaseHelper.getUser(username,password)
    }
    override fun insertPorterVehicle(vehicle : PorterVehicle){
        databaseHelper.insertPorterVehicle(vehicle)
    }
    override fun getPorterVehicleList():MutableList<PorterVehicle>{
        return databaseHelper.getPorterVehicle()
    }

    override fun insertOrder(order : Order){
        databaseHelper.insertOrder(order)
    }
    override fun getOrderList(userId : Int):MutableList<Order>{
        return databaseHelper.getOrders(userId)
    }
}