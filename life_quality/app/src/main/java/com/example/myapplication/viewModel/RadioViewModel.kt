package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel

class RadioViewModel : ViewModel() {

 //   var selectRadioButtonId : Int = -1 //초기값.
    private val radioMap : MutableMap<Int,Int> = mutableMapOf()
    fun setRadioButton(questionId: Int, radioButtonId: Int) {
        radioMap[questionId] = radioButtonId
    }

    fun getRadioButton(questionId: Int): Int {
        return radioMap[questionId]  ?: -1
    }
}
