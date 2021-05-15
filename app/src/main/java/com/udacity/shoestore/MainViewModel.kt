package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel: ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()

    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        _shoesList.value = mutableListOf(
            Shoe("TRACK 6 UTILITY GUM SOLE", 42.0, "Ananas", "LOW TOP - NAVY PEONY/GUM",
                mutableListOf("pro_a67008_1")),
            Shoe("ANANAS TRACK 6 SUEDE MOONPHASE", 43.0, "Ananas", "LOW TOP - GREY PEPPLE",
                mutableListOf("pro_a6t005_1")),
            Shoe("ANANAS X DORAEMON 50 YEARS PATTAS", 39.0, "Ananas", "WHITE/SUNRISE 50TH",
                mutableListOf("pro_a61112_1")))

    }

    fun addShoe(shoe: Shoe): Boolean {
        return _shoesList.value?.add(shoe) ?: false
    }

}