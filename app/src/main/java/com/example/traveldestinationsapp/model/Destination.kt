package com.example.traveldestinationsapp.model

import java.io.Serializable

data class Destination(
    val img: Int,
    val name: String,
    val country: String,
    var rating: Int,
    val description: String
) : Serializable
