package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.EduYosilBinding

class EduYosilActivity : AppCompatActivity() {
    var yosil_models = mutableListOf<Int>()
    var yosil_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EduYosilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        yosil_models.add(R.drawable.edu_yosil_1)
        yosil_models.add(R.drawable.edu_yosil_2)
        yosil_models.add(R.drawable.edu_yosil_3)
        yosil_models.add(R.drawable.edu_yosil_4)
        yosil_models.add(R.drawable.edu_yosil_5)
        yosil_models.add(R.drawable.edu_yosil_6)




        yosil_colors.add(getColor(R.color.edu_yosil_color1))
        yosil_colors.add(getColor(R.color.edu_yosil_color2))
        yosil_colors.add(getColor(R.color.edu_yosil_color3))
        yosil_colors.add(getColor(R.color.edu_yosil_color4))
        yosil_colors.add(getColor(R.color.edu_yosil_color5))
        yosil_colors.add(getColor(R.color.edu_yosil_color6))




        var adapter = EduYosilAdapter(yosil_models, this)
        binding.yosilViewPager.adapter = adapter
        binding.yosilViewPager.clipToPadding = false
        binding.yosilViewPager.setPadding(50, 0, 50, 0)
        binding.yosilViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < adapter.count-1 && position < yosil_colors.size - 1) {
                    binding.yosilViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            yosil_colors[position],
                            yosil_colors[position+1]
                        ) as Int
                    )
                }
                else {
                    binding.yosilViewPager.setBackgroundColor(yosil_colors[yosil_colors.size-1])
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }
}