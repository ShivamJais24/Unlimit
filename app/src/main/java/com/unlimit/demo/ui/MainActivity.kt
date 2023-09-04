package com.unlimit.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.unlimit.demo.database.JokeDao
import com.unlimit.demo.database.JokeDatabase
import com.unlimit.demo.util.MyViewModelFactory
import com.unlimit.demo.util.RetrofitService
import com.velmurugan.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding
    private lateinit var bookDao: JokeDao
    var postion=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter
        val db = Room.databaseBuilder(
            applicationContext,
            JokeDatabase::class.java, "joke_database"
        ).build()
        bookDao = db.jokeDao()


        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]


        viewModel.joke.observe(this) {
            if(postion!=10)
            {
                postion++
            }
            lifecycleScope.launch(Dispatchers.IO) {
                bookDao.insertJoke(Joke(postion,it.joke))
                val list = bookDao.getAllJoke()
                adapter.setMovies(list)
                runOnUiThread(Runnable {
                    adapter.notifyDataSetChanged()

                })

            }

        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(this, Observer { showLoading ->
            binding.progressDialog.visibility = if (showLoading) View.VISIBLE else View.GONE
        })




        viewModel.getJokes()

    }



}