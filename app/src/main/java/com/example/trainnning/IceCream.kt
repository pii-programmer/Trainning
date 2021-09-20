package com.example.trainnning

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iceCream_table")
data class IceCream(
    @PrimaryKey(autoGenerate = true)
    var id: Long?  = null,
    var icon: String?,
    var title: String?,
    var text: String?
)
// 主キーidはautoGenerateになる。よってinsertの際、idを指定しないこと。