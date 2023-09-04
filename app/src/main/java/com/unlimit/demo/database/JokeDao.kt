package com.unlimit.demo.database

import androidx.room.*
import com.unlimit.demo.ui.Joke

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(book: Joke)
 
    @Query("SELECT * FROM Joke")
    fun getAllJoke(): List<Joke>
 
    @Update
    suspend fun updateJoke(book: Joke)
 
    @Delete
    suspend fun deleteJoke(book: Joke)
 
}
 