package me.brunofelix.spanishverbs.data

import androidx.paging.PagingSource
import me.brunofelix.spanishverbs.utils.Constants

class VerbRepositoryImpl constructor(
    private val dao: VerbDao
) : VerbRepository {

    override suspend fun insert(verb: Verb): Result<Long> {
        return try {
            val result = dao.insert(verb)

            if (result > 0) {
                Result.Success(result)
            } else {
                Result.Error(Constants.GENERIC_ERROR)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: Constants.GENERIC_ERROR)
        }
    }

    override suspend fun findById(id: Long): Result<Verb> {
        return try {
            val verb = dao.findById(id)

            if (verb != null) {
                Result.Success(verb)
            } else {
                Result.Error(Constants.NOT_FOUND_ERROR)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: Constants.GENERIC_ERROR)
        }
    }

    override fun findAll(): PagingSource<Int, Verb> = dao.findAll()
}