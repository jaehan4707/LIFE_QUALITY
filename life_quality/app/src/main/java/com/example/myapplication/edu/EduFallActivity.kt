package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityEduFallBinding
import com.example.myapplication.databinding.EduMouthBinding

class EduFallActivity : AppCompatActivity() {
    var fall_models = mutableListOf<Int>()
    var fall_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityEduFallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fall_models.add(R.drawable.edu_fall_1)
        fall_models.add(R.drawable.edu_fall_2)
        fall_models.add(R.drawable.edu_fall_3)
        fall_models.add(R.drawable.edu_fall_4)
        fall_models.add(R.drawable.edu_fall_5)
        fall_models.add(R.drawable.edu_fall_6)
        fall_models.add(R.drawable.edu_fall_7)
        fall_models.add(R.drawable.edu_fall_8)
        fall_models.add(R.drawable.edu_fall_9)
        fall_models.add(R.drawable.edu_fall_10)
        fall_models.add(R.drawable.edu_fall_11)

        fall_colors.add(getColor(R.color.edu_mouth_color1))
        fall_colors.add(getColor(R.color.edu_mouth_color2))
        fall_colors.add(getColor(R.color.edu_mouth_color3))
        fall_colors.add(getColor(R.color.edu_mouth_color1))
        fall_colors.add(getColor(R.color.edu_mouth_color2))
        fall_colors.add(getColor(R.color.edu_mouth_color3))
        fall_colors.add(getColor(R.color.edu_mouth_color1))
        fall_colors.add(getColor(R.color.edu_mouth_color2))
        fall_colors.add(getColor(R.color.edu_mouth_color3))
        fall_colors.add(getColor(R.color.edu_mouth_color1))
        fall_colors.add(getColor(R.color.edu_mouth_color1))

        var adapter = EduAdapter(fall_models, this)
        binding.fallViewPager.adapter = adapter
        binding.maxPage.text = adapter.count.toString()
        binding.fallViewPager.clipToPadding = false
        binding.fallViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < adapter.count - 1 && position < fall_colors.size - 1) {
                    binding.fallViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            fall_colors[position],
                            fall_colors[position + 1]
                        ) as Int
                    )
                } else {
                    binding.fallViewPager.setBackgroundColor(fall_colors[fall_colors.size - 1])
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