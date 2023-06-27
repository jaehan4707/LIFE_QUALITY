package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.EduExcerciseBinding

class EduExcerciseActivity : AppCompatActivity() {
    var excercise_models = mutableListOf<Int>()
    var excercise_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EduExcerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        excercise_models.add(R.drawable.edu_ipaq_1)
        excercise_models.add(R.drawable.edu_ipaq_2)
        excercise_models.add(R.drawable.edu_ipaq_3)
        excercise_models.add(R.drawable.edu_ipaq_4)
        excercise_models.add(R.drawable.edu_ipaq_5)
        excercise_models.add(R.drawable.edu_ipaq_6)
        excercise_models.add(R.drawable.edu_ipaq_7)
        excercise_models.add(R.drawable.edu_ipaq_8)
        excercise_models.add(R.drawable.edu_ipaq_9)
        excercise_models.add(R.drawable.edu_ipaq_10)
        excercise_models.add(R.drawable.edu_ipaq_11)
        excercise_models.add(R.drawable.edu_ipaq_12)
        excercise_models.add(R.drawable.edu_ipaq_13)
        excercise_colors.add(getColor(R.color.edu_excercise_color1))
        excercise_colors.add(getColor(R.color.edu_excercise_color2))
        excercise_colors.add(getColor(R.color.edu_excercise_color3))
        excercise_colors.add(getColor(R.color.edu_excercise_color4))
        excercise_colors.add(getColor(R.color.edu_excercise_color5))
        excercise_colors.add(getColor(R.color.edu_excercise_color1))
        excercise_colors.add(getColor(R.color.edu_excercise_color2))
        excercise_colors.add(getColor(R.color.edu_excercise_color3))
        excercise_colors.add(getColor(R.color.edu_excercise_color4))
        excercise_colors.add(getColor(R.color.edu_excercise_color5))
        excercise_colors.add(getColor(R.color.edu_excercise_color1))
        excercise_colors.add(getColor(R.color.edu_excercise_color2))
        excercise_colors.add(getColor(R.color.edu_excercise_color3))


        var adapter = EduAdapter(excercise_models, this)
        binding.maxPage.text = adapter.count.toString()
        binding.excerciseViewPager.adapter = adapter
        binding.excerciseViewPager.clipToPadding = false
        binding.excerciseViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < adapter.count-1 && position < excercise_colors.size - 1) {
                    binding.excerciseViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            excercise_colors[position],
                            excercise_colors[position+1]
                        ) as Int
                    )
                }
                else {
                    binding.excerciseViewPager.setBackgroundColor(excercise_colors[excercise_colors.size-1])
                }
            }

            override fun onPageSelected(position: Int) {
                binding.currentPage.text = (position+1).toString()+"/"
                binding.maxPage.text=adapter.count.toString()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }
}