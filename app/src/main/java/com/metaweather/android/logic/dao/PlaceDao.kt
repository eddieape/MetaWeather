package com.metaweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.metaweather.android.MetaWeatherApplication
import com.metaweather.android.logic.model.Place

object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("pace", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("pace")

    private fun sharedPreferences() =
        MetaWeatherApplication.context.getSharedPreferences("mate_weather", Context.MODE_PRIVATE)
}