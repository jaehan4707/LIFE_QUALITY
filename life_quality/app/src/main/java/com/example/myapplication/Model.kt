package com.example.myapplication

class Model {
    var image : Int = 0
    var title : String = ""
    var desc : String = ""

    constructor()
    constructor(image : Int, title : String, desc : String) {
        this.image = image
        this.title = title
        this.desc = desc
    }

}