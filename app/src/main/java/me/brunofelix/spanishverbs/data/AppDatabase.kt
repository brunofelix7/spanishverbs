package me.brunofelix.spanishverbs.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Verb::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun verbDao(): VerbDao
}