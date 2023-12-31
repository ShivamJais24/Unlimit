package com.unlimit.demo

import com.unlimit.demo.ui.Joke
import com.unlimit.demo.ui.MainRepository
import com.unlimit.demo.util.NetworkState
import com.unlimit.demo.util.RetrofitService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun `get all movie test`() {
        runBlocking {
            Mockito.`when`(apiService.getJokes()).thenReturn(Response.success(Joke(1,"first")))
            val response = mainRepository.getJoke()
            assertEquals(Joke(1,"first"),  NetworkState.Success(response).data)
        }

    }

}