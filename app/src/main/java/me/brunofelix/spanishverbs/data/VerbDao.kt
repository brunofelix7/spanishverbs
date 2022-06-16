package me.brunofelix.spanishverbs.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VerbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(verb: Verb) : Long

    @Query("SELECT * FROM verbs WHERE id = :id")
    suspend fun findById(id: Long) : Verb?

    @Query("SELECT * FROM verbs")
    fun findAll(): PagingSource<Int, Verb>
}