package me.brunofelix.spanishverbs.data.json

import com.google.gson.annotations.SerializedName

data class Preterito(
    @SerializedName("yo")
    val yo: String,

    @SerializedName("tu")
    val tu: String,

    @SerializedName("el_ella_ud")
    val elEllaUd: String,

    @SerializedName("nosotros")
    val nosotros: String,

    @SerializedName("vosotros")
    val vosotros: String,

    @SerializedName("ellos_ellas_uds")
    val ellosEllasUds: String
)
