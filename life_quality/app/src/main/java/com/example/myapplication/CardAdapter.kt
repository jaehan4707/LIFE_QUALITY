package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.edu.*
import com.google.android.material.snackbar.Snackbar

class CardAdapter : PagerAdapter{

    var models = mutableListOf<Model>()
    lateinit var layoutInflater: LayoutInflater
    lateinit var context: Context


    constructor()
    constructor(models: MutableList<Model>, context: Context){
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
        var view = layoutInflater.inflate(R.layout.item, container, false)

        var imageView = view.findViewById<ImageView>(R.id.cardImage)
        var title = view.findViewById<TextView>(R.id.cardTitle)
        var desc = view.findViewById<TextView>(R.id.cardDesc)
        imageView.setImageResource(models.get(position).image)
        title.setText(models.get(position).title)
        desc.setText(models.get(position).desc)
        container.addView(view, 0)

        //카드를 클릭했을 때 적용할 이벤트 리스너
        view.setOnClickListener() {
            when(position) {
                0 -> {
                    Snackbar.make(view,"영양관리 클릭!", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(context, EduNutritionActivity::class.java)
                    (context).startActivity(intent)
                }
                1-> {
                    Snackbar.make(view,"우울관리 클릭!", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(context, EduEmotionActivity::class.java)
                    (context).startActivity(intent)
                }
                2-> {
                    Snackbar.make(view,"구강관리 클릭!", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(context, EduMouthActivity::class.java)
                    (context).startActivity(intent)
                }
                3 -> {
                    Snackbar.make(view,"운동관리 클릭!", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(context, EduExcerciseActivity::class.java)
                    (context).startActivity(intent)
                }
                4 -> {
                    Snackbar.make(view,"요실금관리 클릭!", Snackbar.LENGTH_LONG).show()
                    val intent = Intent(context, EduYosilActivity::class.java)
                    (context).startActivity(intent)
                }
            }
        }

//        binding.cardImage.setImageResource(models.get(position).image)
//        binding.cardTitle.text = models.get(position).title
//        binding.cardDesc.text = models.get(position).desc

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}