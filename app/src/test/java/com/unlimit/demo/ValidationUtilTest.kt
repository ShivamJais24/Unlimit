package com.unlimit.demo

import com.unlimit.demo.ui.Joke
import com.unlimit.demo.util.ValidationUtil
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {

    @Test
    fun validateMovieTest() {
        val movie = Joke(1,"test")
        assertEquals(true, ValidationUtil.validateJoke(movie))
    }

    @Test
    fun validateMovieEmptyTest() {
        val movie = Joke(1,"test")
        assertEquals(false, ValidationUtil.validateJoke(movie))
    }

}