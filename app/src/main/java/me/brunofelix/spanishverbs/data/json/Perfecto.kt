package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class Perfecto(
    @SerializedName("futuro")
    val futuro: Futuro,

    @SerializedName("pasado")
    val pasado: Pasado,

    @SerializedName("presente")
    val presente: Presente,

    @SerializedName("preterito")
    val preterito: Preterito,

    @SerializedName("condicional")
    val condicional: Condicional
)