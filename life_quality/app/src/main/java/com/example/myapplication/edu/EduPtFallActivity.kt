package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityEduPtFallBinding

class EduFallPtActivity : AppCompatActivity() {
    var fall_pt_models = mutableListOf<Int>()
    var fall_pt_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityEduPtFallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fall_pt_models.add(R.drawable.edu_fall_pt_01)
        fall_pt_models.add(R.drawable.edu_fall_pt_02)
        fall_pt_models.add(R.drawable.edu_fall_pt_03)
        fall_pt_models.add(R.drawable.edu_fall_pt_04)
        fall_pt_models.add(R.drawable.edu_fall_pt_05)
        fall_pt_models.add(R.drawable.edu_fall_pt_06)
        fall_pt_models.add(R.drawable.edu_fall_pt_07)
        fall_pt_models.add(R.drawable.edu_fall_pt_08)
        fall_pt_models.add(R.drawable.edu_fall_pt_09)
        fall_pt_models.add(R.drawable.edu_fall_pt_10)

        fall_pt_colors.add(getColor(R.color.edu_mouth_color1))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color2))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color3))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color1))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color2))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color3))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color1))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color2))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color3))
        fall_pt_colors.add(getColor(R.color.edu_mouth_color1))
        var adapter = EduAdapter(fall_pt_models, this)
        binding.fallptViewPager.adapter = adapter
        binding.maxPage.text = adapter.count.toString()
        binding.fallptViewPager.clipToPadding = false
        binding.fallptViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < adapter.count - 1 && position < fall_pt_colors.size - 1) {
                    binding.fallptViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            fall_pt_colors[position],
                            fall_pt_colors[position + 1]
                        ) as Int
                    )
                } else {
                    binding.fallptViewPager.setBackgroundColor(fall_pt_colors[fall_pt_colors.size - 1])
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
