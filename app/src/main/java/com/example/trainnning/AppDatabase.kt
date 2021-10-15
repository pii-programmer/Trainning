package com.example.trainnning

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Weather::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun WeatherDao() : WeatherDao
}
// DBのクラスを変えた時は、versionを上げる
// テーブルやカラムを増やす等のDB操作した時は、Migrationする