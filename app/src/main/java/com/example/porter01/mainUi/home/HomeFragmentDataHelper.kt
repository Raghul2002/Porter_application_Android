package com.example.porter01.mainUi.home

import com.example.porter01.R
import com.example.porter01.mainUi.home.gridView.Vehicle

class HomeFragmentDataHelper {
    private lateinit var vehicleList: MutableList<Vehicle>
    fun getVehicleList():MutableList<Vehicle>{
        vehicleList = mutableListOf()
        vehicleList.add( Vehicle(R.drawable.ace,"Ace","Heavy Load"))
        vehicleList.add( Vehicle(R.drawable.tata_407,"Tata 407","Medium Load"))
        vehicleList.add( Vehicle(R.drawable.three_wheeler,"Three Wheeler","Light Load"))
        return vehicleList
    }
}