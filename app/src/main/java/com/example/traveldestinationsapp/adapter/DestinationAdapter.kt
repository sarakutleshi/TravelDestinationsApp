package com.example.traveldestinationsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.traveldestinationsapp.DestinationDetailsActivity
import com.example.traveldestinationsapp.R
import com.example.traveldestinationsapp.model.Destination

class DestinationAdapter(
    private val context: Context,
    private val destinations: List<Destination>,
    private val onRatingChanged: (Int, Int) -> Unit
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = destinations.size

    override fun getItem(position: Int): Any = destinations[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.item_destination, parent, false)

        val ivPhoto = view.findViewById<ImageView>(R.id.ivDestinationPhoto)
        val tvName = view.findViewById<TextView>(R.id.tvDestinationName)
        val tvCountry = view.findViewById<TextView>(R.id.tvDestinationCountry)
        val tvRating = view.findViewById<TextView>(R.id.tvDestinationRating)
        val btnIncreaseRating = view.findViewById<Button>(R.id.btnIncreaseRating)
        val btnDecreaseRating = view.findViewById<Button>(R.id.btnDecreaseRating)

        val destination = destinations[position]

        ivPhoto.setImageResource(destination.img)
        tvName.text = destination.name
        tvCountry.text = destination.country
        tvRating.text = getStarRating(destination.rating)


        ivPhoto.setOnClickListener {
            val intent = Intent(context, DestinationDetailsActivity::class.java).apply {
                putExtra("destinationName", destination.name)
                putExtra("destinationCountry", destination.country)
                putExtra("destinationDescription", destination.description)
                putExtra("destinationRating", destination.rating)
                putExtra("destinationImage", destination.img)
            }
            context.startActivity(intent)
        }


        btnIncreaseRating.setOnClickListener {
            if (destination.rating < 5) {
                destination.rating++
                onRatingChanged(position, destination.rating)
                tvRating.text = getStarRating(destination.rating)
            }
        }


        btnDecreaseRating.setOnClickListener {
            if (destination.rating > 1) {
                destination.rating--
                onRatingChanged(position, destination.rating)
                tvRating.text = getStarRating(destination.rating)
            }
        }

        return view
    }

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



