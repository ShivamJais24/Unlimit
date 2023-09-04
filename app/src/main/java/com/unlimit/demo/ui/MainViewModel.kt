package com.unlimit.demo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlimit.demo.util.NetworkState
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
    get() = _errorMessage

    val joke = MutableLiveData<Joke>()

    var job: Job? = null




    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()


    fun getJokes() {
        loading.value=true
        viewModelScope.launch(Dispatchers.IO) {
            while(isActive) {
                when(val response = mainRepository.getJoke()) {
                    is NetworkState.Success -> {
                        loading.postValue(false)
                        joke.postValue(response.data)
                    }
//                    is NetworkState.Error -> this@MainViewModel.isActive = false
                    else -> {}
                }

                delay(2000)
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}