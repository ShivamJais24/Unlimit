package com.unlimit.demo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unlimit.demo.ui.Joke

@Database(entities = [Joke::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}
 