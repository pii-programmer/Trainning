package com.example.trainnning

import android.widget.AdapterView
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(IceCream::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun iceCreamDao() : IceCreamDao
}