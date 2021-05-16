package com.udacity.shoestore.converter

import androidx.databinding.InverseMethod
import timber.log.Timber

object Converter {

    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double): String {
        return if(value <= 0.0) "" else value.toString()
    }

    @JvmStatic
    fun stringToDouble(value: String): Double {
        return try {value.toDouble()} catch(e: Exception) {0.0}
    }

}