package me.brunofelix.spanishverbs.extensions

import android.content.Context
import timber.log.Timber
import java.io.IOException

fun Context.getJsonFromAssets(jsonName: String) : String {
    var json = ""
    try {
        json = this.assets.open(jsonName).bufferedReader().use { it.readText() }
    } catch (e: IOException) {
        Timber.e(e)
    }
    return json
}