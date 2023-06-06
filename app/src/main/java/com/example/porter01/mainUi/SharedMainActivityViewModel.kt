package com.example.porter01.mainUi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.porter01.model.PorterVehicle
import com.example.porter01.model.Order

class SharedMainActivityViewModel : ViewModel() {
    private val _orderList = MutableLiveData<MutableList<Order>>()
    private val _vehicleList = MutableLiveData<MutableList<PorterVehicle>>()
    val orderList: LiveData<MutableList<Order>> = _orderList
    val vehicleList: LiveData<MutableList<PorterVehicle>> = _vehicleList
    init {
        _orderList.value = mutableListOf()
        _vehicleList.value = mutableListOf()
    }

    fun addOrders(
        from: String,
        to: String,
        date: String,
        time: String,
        cost: Int,
        receiverName: String,
        receiverContactNumber: String,
        noOfHelpers: Int
    ) {
        val order : Order = Order(from, to, date, time, cost, receiverName, receiverContactNumber, noOfHelpers)
        //_orderList.value?.add(order)
    }

}