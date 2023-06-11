package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.EduMouthBinding

class EduMouthActivity : AppCompatActivity() {
    var mouth_models = mutableListOf<Int>()
    var mouth_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EduMouthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mouth_models.add(R.drawable.edu_mouth_1)
        mouth_models.add(R.drawable.edu_mouth_2)
        mouth_models.add(R.drawable.edu_mouth_3)
        mouth_models.add(R.drawable.edu_mouth_4)
        mouth_models.add(R.drawable.edu_mouth_5)
        mouth_models.add(R.drawable.edu_mouth_6)
        mouth_models.add(R.drawable.edu_mouth_7)
        Log.d("test", "구강관")

        mouth_colors.add(getColor(R.color.edu_mouth_color1))
        mouth_colors.add(getColor(R.color.edu_mouth_color2))
        mouth_colors.add(getColor(R.color.edu_mouth_color3))
        mouth_colors.add(getColor(R.color.edu_mouth_color1))
        mouth_colors.add(getColor(R.color.edu_mouth_color2))
        mouth_colors.add(getColor(R.color.edu_mouth_color3))
        mouth_colors.add(getColor(R.color.edu_mouth_color1))

        var adapter = EduAdapter(mouth_models, this)
        binding.mouthViewPager.adapter = adapter
        binding.mouthViewPager.clipToPadding = false
        binding.mouthViewPager.setPadding(50, 0, 50, 0)
        binding.mouthViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < adapter.count - 1 && position < mouth_colors.size - 1) {
                    binding.mouthViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            mouth_colors[position],
                            mouth_colors[position + 1]
                        ) as Int
                    )
                } else {
                    binding.mouthViewPager.setBackgroundColor(mouth_colors[mouth_colors.size - 1])
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }


        })

    }
}