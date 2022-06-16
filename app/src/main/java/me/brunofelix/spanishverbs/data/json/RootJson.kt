package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class RootJson(
    @SerializedName("verbo")
    val verbo: String,

    @SerializedName("perfecto")
    val perfecto: Perfecto,

    @SerializedName("imperativo")
    val imperativo: Imperativo,

    @SerializedName("indicativo")
    val indicativo: Indicativo,

    @SerializedName("progresivo")
    val progresivo: Progresivo,

    @SerializedName("subjuntivo")
    val subjuntivo: Subjuntivo,

    @SerializedName("perfecto_subjuntivo")
    val perfectoSubjuntivo: PerfectoSubjuntivo
)