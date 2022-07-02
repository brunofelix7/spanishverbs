package me.brunofelix.spanishverbs.utils

import android.content.Context
import android.content.res.Resources
import javax.inject.Inject

class AppProvider @Inject constructor(
    private val context: Context
) {

    fun res(): Resources = context.resources

    fun context(): Context = context.applicationContext
}