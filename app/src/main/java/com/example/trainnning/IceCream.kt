package com.example.trainnning

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iceCream_table")
data class IceCream(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val icon: String,
    val title: String,
    val text: String
)
