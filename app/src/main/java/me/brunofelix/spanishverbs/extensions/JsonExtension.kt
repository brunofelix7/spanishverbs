package me.brunofelix.spanishverbs.extensions

import android.content.Context
import timber.log.Timber
import java.io.FileNotFoundException
import java.io.IOException

fun Context.getJsonFromAssets(jsonName: String) : String {
    var json = ""
    try {
        json = this.assets.open(jsonName).bufferedReader().use { it.readText() }
    } catch (e: IOException) {
        Timber.e(e)
    } catch (e: FileNotFoundException) {
        Timber.e(e)
    }
    return json
}

fun listAZ() : List<String> {
    return listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Y", "Z")
}