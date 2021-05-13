package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel: ViewModel() {

    private val _shoesList = MutableLiveData<List<Shoe>>()

    val shoesList: LiveData<List<Shoe>>
        get() = _shoesList

    init {
        _shoesList.value = listOf(
            Shoe("TRACK 6 UTILITY GUM SOLE", 42.0, "Ananas", "LOW TOP - NAVY PEONY/GUM",
                mutableListOf("pro_a67008_1", "pro_a67008_2", "pro_a67008_3")),
            Shoe("ANANAS TRACK 6 SUEDE MOONPHASE", 43.0, "Ananas", "LOW TOP - GREY PEPPLE",
                mutableListOf("pro_a6t005_1", "pro_a6t005_2", "pro_a6t005_3")),
            Shoe("ANANAS X DORAEMON 50 YEARS PATTAS", 39.0, "Ananas", "WHITE/SUNRISE 50TH",
                mutableListOf("pro_a61112_1", "pro_a61112_2", "pro_a61112_3")))

    }

}