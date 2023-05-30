package com.example.myapplication.edu

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
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
        dialog.dismiss() //다이얼로그 닫기.
    }
    @SuppressLint("ResourceType")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.item_education, container, false)

        //이미지뷰 가져오기
        var imageView = view.findViewById<ImageView>(R.id.eduImage)
        val drawable = ContextCompat.getDrawable(context,models[position])
        imageView.setImageResource(models.get(position))
        //val drawable = ContextCompat.getDrawable(context, R.drawable.my_drawable)


        //model에는 drawable
  //      imageView.adjustViewBounds=true
//        imageView.scaleType=imageView
        /*
        imageView.setOnClickListener{
            if(isImageExpanded){
                collapseImage()
            }
            else
                expandImage(position)
        }
         */
        /*
        imageView.setOnClickListener{
            Log.d("test", "이미지 뷰 클릭")
            val expandedImageView = ImageView(context)
            expandedImageView.setImageResource(models[position])
            expandedImageView.adjustViewBounds=true
            expandedImageView.scaleType=ImageView.ScaleType.FIT_CENTER

            dialog = Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.setContentView(expandedImageView)
            dialog.show()
            /*
            dialog?.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK && dialog?.isShowing == true) {
                    backButtonListenr?.onBackPressed()
                    true
                } else {
                    false
                }
            }
             */
            dialog.setOnDismissListener {
                collapseImage()
            }
        }
         */
        container.addView(view, 0)
        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}
