package com.example.myapplication.model

class Time {

    var hour : Int =-1
    var min : Int = -1

    constructor(hour: Int, min: Int) {
        this.hour=hour
        this.min=min
    }
    fun calTime() : Double{
        return (hour*60 + min).toDouble()
    }
}