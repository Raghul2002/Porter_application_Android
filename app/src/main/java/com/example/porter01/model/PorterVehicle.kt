package com.example.porter01.model

//sealed class VehicleTypes{
//    object TATA404:VehicleTypes(){
//        const val capacity: String = "2500kg"
//        const val size :String="9ft x 5.5ft x 6ft"
//        const val localRate = "Rs.14/KM"
//        const val outStationRate = "Rs.20/KM"
//        const val rental = "Rs.140Km"
//    }
//    object ThreeWheeler:VehicleTypes(){
//        const val capacity: String = "500kg"
//        const val size :String="5.5ft x 4.5ft x 5ft"
//        const val localRate = "Rs.8/KM"
//        const val outStationRate = "Rs.13/KM"
//        const val rental = "Rs.80Km"
//    }
//    object Ace: VehicleTypes(){
//        const val capacity: String = "750kg"
//        const val size :String="7ft x 4ft x 5ft"
//        const val localRate = "Rs.10/KM"
//        const val outStationRate = "Rs.15/KM"
//        const val rental = "Rs.100Km"
//    }
//}
data class PorterVehicle(
    val name : String,
    val capacity: String,
    val size: String,
    val localRate: String,
    val outStationRate: String,
    val rentalRate:String,
)