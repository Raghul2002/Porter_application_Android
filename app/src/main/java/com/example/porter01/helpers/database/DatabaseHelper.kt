package com.example.porter01.helpers.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.porter01.model.PorterVehicle
import com.example.porter01.model.Order
import com.example.porter01.model.User

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "PorterApp.db"
        const val USER_TABLE_NAME = "User"
        const val COLUMN_ID = "id"
        const val COLUMN_USER_ID = "userId"
        const val COLUMN_FIRST_NAME = "firstName"
        const val COLUMN_LAST_NAME = "lastName"
        const val COLUMN_USERNAME = "userName"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_PHONE_NO = "phoneNo"
        const val COLUMN_EMAIL = "email"

        const val PORTER_VEHICLE_TABLE_NAME = "PorterVehicle"
        const val COLUMN_VEHICLE_NAME = "name"
        const val COLUMN_VEHICLE_CAPACITY = "capacity"
        const val COLUMN_VEHICLE_SIZE = "size"
        const val COLUMN_VEHICLE_LOCAL_RATE = "localRate"
        const val COLUMN_VEHICLE_OUTSTATION_RATE = "outStationRate"
        const val COLUMN_VEHICLE_RENTAL_RATE = "rentalRate"

        const val ORDER_TABLE_NAME = "PorterOrder"
        const val COLUMN_ORDER_FROM = "fromAddress"
        const val COLUMN_ORDER_TO = "toAddress"
        const val COLUMN_ORDER_DATE = "date"
        const val COLUMN_ORDER_TIME = "time"
        const val COLUMN_ORDER_RECEIVER_NAME = "receiverName"
        const val COLUMN_ORDER_RECEIVER_CONTACT_NUMBER = "receiverNumber"
        const val COLUMN_ORDER_COST = "cost"
        const val COLUMN_ORDER_NO_OF_HELPER = "helpers"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTableQuery = ("CREATE TABLE IF NOT EXISTS $USER_TABLE_NAME "
                + "($COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_FIRST_NAME TEXT, "
                + "$COLUMN_LAST_NAME TEXT, "
                + "$COLUMN_USERNAME TEXT, "
                + "$COLUMN_PASSWORD TEXT, "
                + "$COLUMN_PHONE_NO TEXT, "
                + "$COLUMN_EMAIL TEXT )")

        val createPorterVehicleTableQuery =
            ("CREATE TABLE IF NOT EXISTS $PORTER_VEHICLE_TABLE_NAME "
                    + "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "$COLUMN_VEHICLE_NAME TEXT, "
                    + "$COLUMN_VEHICLE_CAPACITY TEXT, "
                    + "$COLUMN_VEHICLE_SIZE TEXT, "
                    + "$COLUMN_VEHICLE_LOCAL_RATE TEXT, "
                    + "$COLUMN_VEHICLE_OUTSTATION_RATE TEXT, "
                    + "$COLUMN_VEHICLE_RENTAL_RATE TEXT) ")


        val createOrderTableQuery = ("CREATE TABLE IF NOT EXISTS $ORDER_TABLE_NAME "
                + "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_USER_ID INTEGER,"
                + "$COLUMN_ORDER_FROM TEXT, "
                + "$COLUMN_ORDER_TO TEXT, "
                + "$COLUMN_ORDER_DATE DATE, "
                + "$COLUMN_ORDER_TIME TEXT, "
                + "$COLUMN_ORDER_COST INTEGER, "
                + "$COLUMN_ORDER_RECEIVER_NAME TEXT, "
                + "$COLUMN_ORDER_RECEIVER_CONTACT_NUMBER TEXT, "
                + "$COLUMN_ORDER_NO_OF_HELPER TEXT, "
                + "FOREIGN KEY ($COLUMN_USER_ID)  "
                + "REFERENCES $USER_TABLE_NAME($COLUMN_USER_ID) ) ")

        db?.execSQL(createUserTableQuery)
        db?.execSQL(createPorterVehicleTableQuery)
        db?.execSQL(createOrderTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }


    // Users

    fun insertUser(user: User) {
        val values = ContentValues().apply {
            put(COLUMN_FIRST_NAME, user.firstName)
            put(COLUMN_LAST_NAME, user.lastName)
            put(COLUMN_USERNAME, user.userName)
            put(COLUMN_PASSWORD, user.password)
            put(COLUMN_PHONE_NO, user.phoneNo)
            put(COLUMN_EMAIL, user.emailId)
        }
        this.writableDatabase.apply {
            insert(USER_TABLE_NAME, null, values)
            close()
        }
    }


    @SuppressLint("Range")
    fun getUser(_username: String, _password: String): User? {
        val db = this.readableDatabase
        val query =
            "SELECT * FROM $USER_TABLE_NAME WHERE $COLUMN_USERNAME = '$_username' AND $COLUMN_PASSWORD = '$_password'"
        val cursor: Cursor = db.rawQuery(query, null)
        var user: User? = null
        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
            val username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME))
            val phoneNo = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NO))
            val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
            user = User(firstName, lastName, username, password, email, phoneNo, userId)
        }
        cursor.close()
        db.close()
        return user
    }

    @SuppressLint("Range")
    fun getUser(_userId: Int): User? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $USER_TABLE_NAME WHERE $COLUMN_USER_ID = '$_userId'"
        val cursor: Cursor = db.rawQuery(query, null)
        var user: User? = null
        if (cursor.moveToFirst()) {
            val userId = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID))
            val username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME))
            val phoneNo = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NO))
            val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
            user = User(firstName, lastName, username, password, email, phoneNo, userId)
        }
        cursor.close()
        db.close()
        return user
    }


    // Orders
    @SuppressLint("Range")
    fun getOrders(_userId: Int): MutableList<Order> {
        val db = this.readableDatabase
        val query = "SELECT * FROM $ORDER_TABLE_NAME WHERE $COLUMN_USER_ID = '$_userId'"
        val cursor: Cursor = db.rawQuery(query, null)
        val orderList: MutableList<Order> = mutableListOf()
        if (cursor.moveToFirst()) {
            do {
                val from: String = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_FROM))
                val to: String = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TO))
                val date: String = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_DATE))
                val time: String =cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_TIME))
                val cost: Int =cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_COST))
                val receiverName: String =cursor.getString(cursor.getColumnIndex(
                    COLUMN_ORDER_RECEIVER_NAME
                ))
                val receiverContactNumber: String =cursor.getString(cursor.getColumnIndex(
                    COLUMN_ORDER_RECEIVER_CONTACT_NUMBER
                ))
                val noOfHelpers: Int =cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_NO_OF_HELPER))
                val order = Order(from,to,date,time,cost,receiverName,receiverContactNumber, noOfHelpers)
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return orderList
    }
    fun insertOrder(order : Order) {
        val values = ContentValues().apply {
            put(COLUMN_ORDER_FROM, order.from)
            put(COLUMN_ORDER_TO, order.to)
            put(COLUMN_ORDER_DATE, order.date)
            put(COLUMN_ORDER_TIME, order.time)
            put(COLUMN_ORDER_COST, order.cost)
            put(COLUMN_ORDER_RECEIVER_NAME, order.receiverName)
            put(COLUMN_ORDER_RECEIVER_CONTACT_NUMBER, order.receiverContactNumber)
            put(COLUMN_ORDER_NO_OF_HELPER, order.noOfHelpers)
        }
        this.writableDatabase.apply {
            insert(ORDER_TABLE_NAME, null, values)
            close()
        }
    }


    //Porter Vehicle

    @SuppressLint("Range")
    fun getPorterVehicle():MutableList<PorterVehicle> {
        val db = this.readableDatabase
        val query = "SELECT * FROM $PORTER_VEHICLE_TABLE_NAME "
        val cursor: Cursor = db.rawQuery(query, null)
        val vehicleList: MutableList<PorterVehicle> = mutableListOf()
        if (cursor.moveToFirst()) {
            do {
                val name : String = cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_NAME))
                val capacity: String = cursor.getString(cursor.getColumnIndex(
                    COLUMN_VEHICLE_CAPACITY
                ))
                val size: String = cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_SIZE))
                val localRate: String = cursor.getString(cursor.getColumnIndex(
                    COLUMN_VEHICLE_LOCAL_RATE
                ))
                val outStationRate: String = cursor.getString(cursor.getColumnIndex(
                    COLUMN_VEHICLE_OUTSTATION_RATE
                ))
                val rentalRate:String = cursor.getString(cursor.getColumnIndex(
                    COLUMN_VEHICLE_RENTAL_RATE
                ))
                val vehicle = PorterVehicle(name, capacity, size, localRate, outStationRate, rentalRate)
                vehicleList.add(vehicle)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return vehicleList
    }
    fun insertPorterVehicle(vehicle : PorterVehicle) {
        val values = ContentValues().apply {
            put(COLUMN_VEHICLE_NAME, vehicle.name)
            put(COLUMN_VEHICLE_CAPACITY, vehicle.capacity)
            put(COLUMN_VEHICLE_SIZE, vehicle.size)
            put(COLUMN_VEHICLE_LOCAL_RATE, vehicle.localRate)
            put(COLUMN_VEHICLE_OUTSTATION_RATE, vehicle.outStationRate)
            put(COLUMN_VEHICLE_RENTAL_RATE, vehicle.rentalRate)
        }
        this.writableDatabase.apply {
            insert(PORTER_VEHICLE_TABLE_NAME, null, values)
            close()
        }
    }
}