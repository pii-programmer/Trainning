package com.example.trainnning

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null,
    var city:String? = "",
    var dateLabel:String? = "",
    var telop:String? = ""
)