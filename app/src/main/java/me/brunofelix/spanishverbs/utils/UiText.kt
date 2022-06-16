package me.brunofelix.spanishverbs.utils

import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any // todo: %d
    ) : UiText()
}

/*
* HOW CAN WE USE IT?
*
* val NAME = "Bruno"
* UiText.DynamicString("This is $NAME")
* UiText.StringResource(
*                   R.string.my_string
*                   NAME)
*
* INSIDE MY RES STRING
* This is %s
*
*/