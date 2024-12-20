package com.example.traveldestinationsapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.traveldestinationsapp.DestinationDetailsActivity
import com.example.traveldestinationsapp.R
import com.example.traveldestinationsapp.adapter.DestinationAdapter
import com.example.traveldestinationsapp.model.Destination

class DestinationsActivity : AppCompatActivity() {

    private lateinit var destinationList: MutableList<Destination>
    private lateinit var adapter: DestinationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        destinationList = createDestinations()
        adapter = DestinationAdapter(this, destinationList) { position, newRating ->
            destinationList[position].rating = newRating
            adapter.notifyDataSetChanged()
        }

        val listView = findViewById<ListView>(R.id.lvDestinations)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DestinationDetailsActivity::class.java).apply {
                putExtra("destination", destinationList[position])
            }
            startActivity(intent)
        }

        val btnSortHighToLow = findViewById<Button>(R.id.btnSortHighToLow)
        val btnSortLowToHigh = findViewById<Button>(R.id.btnSortLowToHigh)

        btnSortHighToLow.setOnClickListener {
            destinationList.sortByDescending { it.rating }
            adapter.notifyDataSetChanged()
        }

        btnSortLowToHigh.setOnClickListener {
            destinationList.sortBy { it.rating }
            adapter.notifyDataSetChanged()
        }
    }

    private fun createDestinations(): MutableList<Destination> {
        return mutableListOf(
            Destination(
                R.drawable.image1,
                "Eiffel Tower",
                "France",
                5,
                "A famous landmark in Paris."
            ),
            Destination(
                R.drawable.image2,
                "Great Wall",
                "China",
                4,
                "A historic wall stretching across China."
            ),
            Destination(
                R.drawable.image3,
                "Grand Canyon",
                "USA",
                5,
                "A breathtaking canyon in Arizona."
            ),
            Destination(
                R.drawable.image4,
                "The Colosseum",
                "Italy",
                2,
                "The Colosseum, an iconic symbol of Ancient Rome."
            ),
            Destination(
                R.drawable.image5,
                "Stonehenge",
                "England",
                5,
                "Stonehenge, a prehistoric monument in Wiltshire, England, is shrouded in mystery."
            ),

            )
    }
}
