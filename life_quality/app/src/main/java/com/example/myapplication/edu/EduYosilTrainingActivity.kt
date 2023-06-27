package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityEduYosilTrainingBinding
import com.example.myapplication.databinding.EduYosilBinding

class EduYosilTrainingActivity : AppCompatActivity() {
    var yosil_models = mutableListOf<Int>()
    var yosil_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityEduYosilTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        yosil_models.add(R.drawable.edu_yosil_training_1)
        yosil_models.add(R.drawable.edu_yosil_training_2)
        yosil_models.add(R.drawable.edu_yosil_training_3)
        yosil_models.add(R.drawable.edu_yosil_training_4)
        yosil_models.add(R.drawable.edu_yosil_training_5)
        yosil_models.add(R.drawable.edu_yosil_training_6)




        yosil_colors.add(getColor(R.color.edu_yosil_color1))
        yosil_colors.add(getColor(R.color.edu_yosil_color2))
        yosil_colors.add(getColor(R.color.edu_yosil_color3))
        yosil_colors.add(getColor(R.color.edu_yosil_color4))
        yosil_colors.add(getColor(R.color.edu_yosil_color5))
        yosil_colors.add(getColor(R.color.edu_yosil_color6))




        var adapter = EduAdapter(yosil_models, this)
        binding.yosilTrainingViewPager.adapter = adapter
        binding.yosilTrainingViewPager.clipToPadding = false
        binding.yosilTrainingViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < adapter.count-1 && position < yosil_colors.size - 1) {
                    binding.yosilTrainingViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            yosil_colors[position],
                            yosil_colors[position+1]
                        ) as Int
                    )
                }
                else {
                    binding.yosilTrainingViewPager.setBackgroundColor(yosil_colors[yosil_colors.size-1])
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