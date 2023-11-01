package com.phyothinzaraung.unitconverterapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class MyViewModel: ViewModel() {

    var tempC by mutableStateOf(0)

    fun convertTemperature(tempF: String){
        var tempFnum = tempF.toInt()
        tempC = ((tempFnum - 32)*0.5556).roundToInt()
    }
}