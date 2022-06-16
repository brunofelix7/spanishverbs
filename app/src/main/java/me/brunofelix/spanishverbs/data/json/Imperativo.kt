package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class Imperativo(
    @SerializedName("afirmativo")
    val afirmativo: Afirmativo,

    @SerializedName("negativo")
    val negativo: Negativo
)
