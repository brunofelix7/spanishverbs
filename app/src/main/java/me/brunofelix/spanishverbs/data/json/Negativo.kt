package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class Negativo(
    @SerializedName("tu")
    val tu: String,

    @SerializedName("usted")
    val usted: String,

    @SerializedName("nosotros")
    val nosotros: String,

    @SerializedName("vosotros")
    val vosotros: String,

    @SerializedName("ustedes")
    val ustedes: String
)
