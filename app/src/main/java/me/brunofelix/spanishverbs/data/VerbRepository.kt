package me.brunofelix.spanishverbs.data

import androidx.paging.PagingSource

interface VerbRepository {
    suspend fun insert(verb: Verb) : Result<Long>
    suspend fun findById(id: Long) : Result<Verb>
    fun findAll(): PagingSource<Int, Verb>
}