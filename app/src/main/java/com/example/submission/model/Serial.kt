package com.example.submission.model

data class Serial(
    val id: Long,
    val image: String,
    val title: String,
    val desc: String,
    val rate: String,
    var isFav: Boolean = false
)