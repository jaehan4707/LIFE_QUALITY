package com.example.myapplication.model

import com.example.myapplication.question.QuestionMainpage

class Bmi {

    var height: Double= 0.0
    var weight: Double = 0.0

    constructor(height: Double, weight: Double) {
        this.height = height
        this.weight = weight
    }
    fun returnBmi(): Bmi {
        return Bmi(height, weight)
    }
    fun calBmi(): Double {
        return (weight / (height/100 * height/100))
    }
}