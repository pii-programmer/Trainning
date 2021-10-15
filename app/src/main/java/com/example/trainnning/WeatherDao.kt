package com.example.trainnning

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert
    fun insert(weather: ArrayList<Weather>)

    @Query("SELECT * FROM weather_table")
    fun selectAll():List<Weather>

    @Query("DELETE FROM weather_table")
    fun deleteAll()
}
// @Query("INSERT INTO WEATHER_TABLE DEFAULT VALUES")