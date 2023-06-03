package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel

class RadioViewModel : ViewModel() {

 //   var selectRadioButtonId : Int = -1 //초기값.
    private val radioMap : MutableMap<Int,Int> = mutableMapOf() //라디오 버튼 결과.
    fun setRadioButton(questionId: Int, radioButtonId: Int) {
        radioMap[questionId] = radioButtonId
    }

    fun getRadioButton(questionId: Int): Int {
        return radioMap[questionId]  ?: -1
    }
    private val editMap : MutableMap<Int,Int> = mutableMapOf() //
    fun setCheckList(idx : Int,flag : Boolean){
        checkList[idx]=flag
    }
    fun getCheckList(idx : Int) : Boolean{
        return checkList[idx]
    }
    private val checkList : BooleanArray = BooleanArray(3)

}
