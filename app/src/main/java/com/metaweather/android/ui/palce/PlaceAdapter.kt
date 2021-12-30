package com.metaweather.android.ui.palce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.metaweather.android.R
import com.metaweather.android.logic.model.Place
import com.metaweather.android.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_weather.*

class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val placeName: TextView = view.findViewById(R.id.placeName)
            val placeAddress: TextView = view.findViewById(R.id.placeAddress)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeAddress.text = place.address
        holder.placeName.text = place.name
        holder.itemView.setOnClickListener {
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                activity.drawerLayout.closeDrawers()
                activity.viewModel.locationLat = place.location.lat
                activity.viewModel.locationLng = place.location.lng
                activity.viewModel.placeName = place.name
                activity.refreshWeather()
            } else {
                val intent = Intent(fragment.context, WeatherActivity::class.java).apply {
                    putExtra("location_lng", place.location.lng)
                    putExtra("location_lat", place.location.lat)
                    putExtra("place_name", place.name)
                }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
            fragment.viewModel.savePlace(place)
        }

    }

    override fun getItemCount() = placeList.size

}