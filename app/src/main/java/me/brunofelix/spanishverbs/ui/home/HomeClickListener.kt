package me.brunofelix.spanishverbs.ui.home

import me.brunofelix.spanishverbs.data.Verb

interface HomeClickListener {
    fun onItemClick(verb: Verb)
    fun onFavoriteClick(verb: Verb)
}