package com.example.trainnning

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("INSERT INTO WEATHER_TABLE DEFAULT VALUES")
    fun insertAll()

    @Query("SELECT * FROM weather_table")
    fun selectAll():List<Weather>

    @Query("DELETE FROM weather_table")
    fun deleteAll()
}