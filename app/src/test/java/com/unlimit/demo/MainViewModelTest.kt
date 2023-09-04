package com.unlimit.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.unlimit.demo.*
import com.unlimit.demo.ui.Joke
import com.unlimit.demo.ui.MainRepository
import com.unlimit.demo.ui.MainViewModel
import com.unlimit.demo.util.NetworkState
import com.unlimit.demo.util.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {


    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = mock(MainRepository::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getJokes() {
        runBlocking {
            Mockito.`when`(mainRepository.getJoke())
                .thenReturn(NetworkState.Success(Joke(1,"joke")))
            mainViewModel.getJokes()
            val result = mainViewModel.joke.getOrAwaitValue()
            assertEquals(Joke(1,"movie"), result)
        }
    }


    @Test
    fun `empty movie list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getJoke())
                .thenReturn(NetworkState.Success(Joke(1,"joke")))
            mainViewModel.getJokes()
            val result = mainViewModel.joke.getOrAwaitValue()
            assertEquals(Joke(1,"joke"), result)
        }
    }

}