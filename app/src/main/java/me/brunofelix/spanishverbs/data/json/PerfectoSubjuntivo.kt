package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class PerfectoSubjuntivo(
    @SerializedName("futuro")
    val futuro: Futuro,

    @SerializedName("pasado")
    val pasado: Pasado,

    @SerializedName("presente")
    val presente: Presente,
)
