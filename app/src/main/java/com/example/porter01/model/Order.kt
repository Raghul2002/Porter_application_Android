package com.example.porter01.model

data class Order (
    val from:String,
    val to : String,
    val date : String,
    val time : String,
    val cost : Int,
    val receiverName : String,
    val receiverContactNumber : String,
    val noOfHelpers:Int,
)