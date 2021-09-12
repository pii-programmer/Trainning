package com.example.trainnning

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IceCreamDao {
    @Insert
    fun insert(iceCream: IceCream)

    @Query("SELECT * FROM iceCream_table")
    fun selectAll():List<IceCream>
}