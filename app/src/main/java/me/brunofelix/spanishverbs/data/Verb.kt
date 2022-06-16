package me.brunofelix.spanishverbs.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "verbs")
@Parcelize
data class Verb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val gerund: String,
    val conjugation: String,
    val isFavorite: Boolean = false
) : Parcelable
