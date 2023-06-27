package com.example.myapplication.edu

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R
class EduAdapter : PagerAdapter{

    var models = mutableListOf<Int>()
    lateinit var layoutInflater: LayoutInflater
    lateinit var context: Context
    lateinit var dialog: Dialog
    var isImageExpanded =false
    interface BackButtonListenr{
        fun onBackPressed()
    }
    private var backButtonListenr : BackButtonListenr?=null

    constructor(models: MutableList<Int>, context: Context){
        this.models = models
        this.context = context
    }

    fun setBackBussttonListener(listener : BackButtonListenr){
        backButtonListenr=listener
    }
    override fun getCount(): Int {
        return models.size
    }
    private var instantiatedViews = SparseArray<View>()
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    fun expandImage(position: Int){
        if(!isImageExpanded){
            isImageExpanded=true
            val expandedImageView = ImageView(context)
            expandedImageView.setImageResource(models[position])
            expandedImageView.adjustViewBounds=true
            expandedImageView.scaleType=ImageView.ScaleType.FIT_CENTER

            dialog = Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setContentView(expandedImageView)
            dialog.show()

            dialog.setOnDismissListener {
                collapseImage()
            }
        }
    }
    fun collapseImage(){
        isImageExpanded=false

    }
    @SuppressLint("ResourceType")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val reusedView = instantiatedViews[position]
        if (reusedView != null) {
            Log.d("problem","재사용뷰?")
            container.addView(reusedView)
            return reusedView
        }
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_education, container, false)

        // 이미지뷰 가져오기
        val imageView = view.findViewById<ImageView>(R.id.eduImage)
        imageView.setImageResource(models[position])
        Log.d("problem","포지션 :${position}")
        container.addView(view, 0)
        instantiatedViews.put(position, view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
        Log.d("problem","${instantiatedViews.size()}")
        instantiatedViews.remove(position)

    }
}
