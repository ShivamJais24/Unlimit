package com.unlimit.demo.util

import com.unlimit.demo.ui.Joke

object ValidationUtil {

    fun validateJoke(joke: Joke) : Boolean {
        if (joke.joke.isNotEmpty()) {
            return true
        }
        return false
    }
}