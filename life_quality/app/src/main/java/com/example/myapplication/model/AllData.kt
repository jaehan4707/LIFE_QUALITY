package com.example.myapplication





class TotalSurvey {
    var surveyType : String = ""
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()
    var selected : Boolean = false
    constructor()

     constructor(surveyType : String,id : String,number : String, title : String, type : String, answer : MutableMap<String, String>,selected : Boolean) {
        this.surveyType = surveyType
         this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
        this.selected=selected
    }
}

class EQ5D {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class EQVAS {
    var id : String =""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class Fall {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class Frailty {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class IPAQ {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class MNA {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class MouthHealth {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}
class SGDSK {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}

class SleepHabit {
    var id : String = ""
    var number : String = ""
    var title : String = ""
    var type : String = ""
    var answer : MutableMap<String, String> = mutableMapOf()

    constructor()

    constructor(id : String, number : String, title : String, type : String, answer : MutableMap<String, String>) {
        this.id = id
        this.number = number
        this.title = title
        this.type = type
        this.answer = answer
    }
}