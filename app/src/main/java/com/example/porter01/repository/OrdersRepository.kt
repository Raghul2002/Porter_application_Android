package com.example.porter01.repository

import com.example.porter01.model.Order

interface OrdersRepository {
    fun insertOrder(order : Order)
    fun getOrderList(userId : Int):MutableList<Order>
}