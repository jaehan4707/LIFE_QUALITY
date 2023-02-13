package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class EduExcerciseAdapter : PagerAdapter{

    var models = mutableListOf<Int>()
    lateinit var layoutInflater: LayoutInflater
    lateinit var context: Context

    constructor()
    constructor(models: MutableList<Int>, context: Context){
        this.models = models
        this.context = context
    }


    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.item_education, container, false)

        //이미지뷰 가져오기
        var imageView = view.findViewById<ImageView>(R.id.eduImage)
        imageView.setImageResource(models.get(position))

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}