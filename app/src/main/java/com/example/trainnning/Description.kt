package com.example.trainnning
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.PrimaryKey
//
//@Entity (tableName = "description_table",
//    foreignKeys = arrayOf(
//        ForeignKey(entity = Weather::class,
//            parentColumns = arrayOf("weather_id"),
//            childColumns = arrayOf("description_id"),
//            onDelete = ForeignKey.CASCADE)
//    )
//)
//data class Description (
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name= "description_id")
//    var id:Long? = null,
//    var headlineText:String? = null,
//    var bodyText:String? = null
//)