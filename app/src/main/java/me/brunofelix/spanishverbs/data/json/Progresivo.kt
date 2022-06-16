package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class Progresivo(
    @SerializedName("futuro")
    val futuro: Futuro,

    @SerializedName("presente")
    val presente: Presente,

    @SerializedName("preterito")
    val preterito: Preterito,

    @SerializedName("imperfecto")
    val imperfecto: Imperfecto,

    @SerializedName("condicional")
    val condicional: Condicional
)
