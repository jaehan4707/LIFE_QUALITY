package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.EduNutritionBinding

class EduNutritionActivity : AppCompatActivity() {
    var nutrition_models = mutableListOf<Int>()
    var nutrition_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EduNutritionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nutrition_models.add(R.drawable.edu_mna_1)
        nutrition_models.add(R.drawable.edu_mna_2)
        nutrition_models.add(R.drawable.edu_mna_3)
        nutrition_models.add(R.drawable.edu_mna_4)
        nutrition_models.add(R.drawable.edu_mna_5)
        nutrition_models.add(R.drawable.edu_mna_6)
        nutrition_models.add(R.drawable.edu_mna_7)
        nutrition_models.add(R.drawable.edu_mna_8)
        nutrition_models.add(R.drawable.edu_mna_9)
        nutrition_models.add(R.drawable.edu_mna_10)
        nutrition_models.add(R.drawable.edu_mna_11)
        nutrition_models.add(R.drawable.edu_mna_12)
        nutrition_colors.add(getColor(R.color.edu_nutri_color1))
        nutrition_colors.add(getColor(R.color.edu_nutri_color2))
        nutrition_colors.add(getColor(R.color.edu_nutri_color3))
        nutrition_colors.add(getColor(R.color.edu_nutri_color1))
        nutrition_colors.add(getColor(R.color.edu_nutri_color2))
        nutrition_colors.add(getColor(R.color.edu_nutri_color3))
        nutrition_colors.add(getColor(R.color.edu_nutri_color1))
        nutrition_colors.add(getColor(R.color.edu_nutri_color2))
        nutrition_colors.add(getColor(R.color.edu_nutri_color3))
        nutrition_colors.add(getColor(R.color.edu_nutri_color1))
        nutrition_colors.add(getColor(R.color.edu_nutri_color2))
        nutrition_colors.add(getColor(R.color.edu_nutri_color3))


        var adapter = EduAdapter(nutrition_models, this)
        binding.nutriViewPager.adapter = adapter
        binding.maxPage.text = adapter.count.toString()
        binding.nutriViewPager.clipToPadding = false
        binding.nutriViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < adapter.count-1 && position < nutrition_colors.size - 1) {
                    binding.nutriViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            nutrition_colors[position],
                            nutrition_colors[position+1]
                        ) as Int
                    )
                }
                else {
                    binding.nutriViewPager.setBackgroundColor(nutrition_colors[nutrition_colors.size-1])
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