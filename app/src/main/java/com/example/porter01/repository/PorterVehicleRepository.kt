package com.example.porter01.repository

import com.example.porter01.model.PorterVehicle

interface PorterVehicleRepository {
    fun insertPorterVehicle(vehicle: PorterVehicle)
    fun getPorterVehicleList():MutableList<PorterVehicle>
}
