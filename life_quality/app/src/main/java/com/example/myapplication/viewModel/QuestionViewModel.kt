package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.SplashActivity.Companion._result
import com.example.myapplication.model.Bmi
import com.example.myapplication.model.Time

class QuestionViewModel : ViewModel() {

 //   var selectRadioButtonId : Int = -1 //초기값.
    private val radioMap : MutableMap<Int,Int> = mutableMapOf() //라디오 버튼 결과.
    private val checkList : BooleanArray = BooleanArray(3)
    private val numberMap : MutableMap<Int,Int> = mutableMapOf()
    private val timeMap : MutableMap<Int, Time> = mutableMapOf()
    private val BmiMap : MutableMap<Int, Bmi> = mutableMapOf()
    fun setRadioButton(questionId: Int, radioButtonId: Int){
        radioMap[questionId] = radioButtonId
    }
    fun getRadioButton(questionId: Int): Int {
        return radioMap[questionId]  ?: -1
    }
    fun setCheckList(idx : Int,flag : Boolean){
        checkList[idx]=flag
    }
    fun getCheckList(idx : Int) : Boolean{
        return checkList[idx]
    }
    fun setTimeMap(idx : Int, hour : Int, min : Int){
        Log.d("viewModel"," idx : ${idx}, hour : ${hour}, min : ${min}")
        timeMap[idx]=Time(hour,min)
    }
    fun getTimeMap(idx : Int): Time{
        return timeMap[idx] ?:Time(-1,-1)
    }
    fun setNumberMap(idx : Int, number : Int){
        numberMap[idx]= number
    }
    fun getNumberMap(idx : Int) : Int{
        return numberMap[idx] ?:-1
    }
    fun setBmiMap(idx:Int,height : Double, weight : Double){
        Log.d("viewModel"," idx : ${idx}, height : ${height}, weight : ${weight}")
        BmiMap[idx]=Bmi(height*100,weight)
    }
    fun getBmiMap(idx: Int) : Bmi{
        return BmiMap[idx] ?: Bmi(0.0,0.0)
    }
}
