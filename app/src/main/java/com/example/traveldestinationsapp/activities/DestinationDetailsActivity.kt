package com.example.traveldestinationsapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DestinationDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_details)

        // merr te dhenat nga adapteri
        val destinationName = intent.getStringExtra("destinationName")
        val destinationCountry = intent.getStringExtra("destinationCountry")
        val destinationDescription = intent.getStringExtra("destinationDescription")
        val destinationRating = intent.getIntExtra("destinationRating", 0)
        val destinationImage = intent.getIntExtra("destinationImage", 0)

        // inicializo views
        val ivDestinationImage = findViewById<ImageView>(R.id.ivDestinationImage)
        val tvDestinationName = findViewById<TextView>(R.id.tvDestinationName)
        val tvDestinationCountry = findViewById<TextView>(R.id.tvDestinationCountry)
        val tvDestinationDescription = findViewById<TextView>(R.id.tvDestinationDescription)
        val tvDestinationRating = findViewById<TextView>(R.id.tvDestinationRating)

        // dergoja daten views
        ivDestinationImage.setImageResource(destinationImage)
        tvDestinationName.text = destinationName
        tvDestinationCountry.text = destinationCountry
        tvDestinationDescription.text = destinationDescription
        tvDestinationRating.text = "Rating: ${getStarRating(destinationRating)}"
    }

    //rikthen yjet sipas prekjeve qe i prek
    private fun getStarRating(rating: Int): String {
        return when (rating) {
            5 -> "★★★★★"
            4 -> "★★★★☆"
            3 -> "★★★☆☆"
            2 -> "★★☆☆☆"
            1 -> "★☆☆☆☆"
            else -> "☆☆☆☆☆"
        }
    }
}


