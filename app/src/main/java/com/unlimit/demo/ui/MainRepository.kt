package com.unlimit.demo.ui

import com.unlimit.demo.util.NetworkState
import com.unlimit.demo.util.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {


    suspend fun getJoke() : NetworkState<Joke> {
            val response = retrofitService.getJokes()
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    NetworkState.Success(responseBody)
                } else {
                    NetworkState.Error(response)
                }
            } else {
                NetworkState.Error(response)
            }
        }

}