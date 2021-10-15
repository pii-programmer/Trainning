package com.example.trainnning

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weather_id")
    var id:Long? = null,
    var description:String = "",
    var forecasts:String = "",
    var copyright:String = ""
)