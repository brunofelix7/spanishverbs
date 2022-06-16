package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class Subjuntivo(
    @SerializedName("futuro")
    val futuro: Futuro,

    @SerializedName("presente")
    val presente: Presente,

    @SerializedName("imperfecto")
    val imperfecto: Imperfecto,

    @SerializedName("imperfecto2")
    val imperfecto2: Imperfecto,
)
