package com.unlimit.demo.ui

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Joke(@PrimaryKey val key:Int,val joke: String)

