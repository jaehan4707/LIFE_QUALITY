package com.example.myapplication

import android.animation.ArgbEvaluator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.EduEmotionBinding
import com.example.myapplication.databinding.EduExcerciseBinding
import com.example.myapplication.databinding.EduMouthBinding
import com.example.myapplication.databinding.EduNutritionBinding

class EduExcerciseActivity : AppCompatActivity() {
    var excercise_models = mutableListOf<Int>()
    var excercise_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EduExcerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        excercise_models.add(R.drawable.edu_excercise_1)
        excercise_models.add(R.drawable.edu_excercise_2)
        excercise_models.add(R.drawable.edu_excercise_3)
        excercise_models.add(R.drawable.edu_excercise_4)
        excercise_models.add(R.drawable.edu_excercise_5)
        excercise_models.add(R.drawable.edu_excercise_6)
        excercise_models.add(R.drawable.edu_excercise_7)
        excercise_models.add(R.drawable.edu_excercise_8)
        excercise_models.add(R.drawable.edu_excercise_9)
        excercise_models.add(R.drawable.edu_excercise_10)
        excercise_models.add(R.drawable.edu_excercise_11)


        excercise_colors.add(getColor(R.color.edu_excercise_color1))
        excercise_colors.add(getColor(R.color.edu_excercise_color2))
        excercise_colors.add(getColor(R.color.edu_excercise_color3))
        excercise_colors.add(getColor(R.color.edu_excercise_color4))
        excercise_colors.add(getColor(R.color.edu_excercise_color5))
        excercise_colors.add(getColor(R.color.edu_excercise_color6))
        excercise_colors.add(getColor(R.color.edu_excercise_color7))
        excercise_colors.add(getColor(R.color.edu_excercise_color8))
        excercise_colors.add(getColor(R.color.edu_excercise_color9))
        excercise_colors.add(getColor(R.color.edu_excercise_color10))
        excercise_colors.add(getColor(R.color.edu_excercise_color11))


        var adapter = EduMouthAdapter(excercise_models, this)
        binding.excerciseViewPager.adapter = adapter
        binding.excerciseViewPager.clipToPadding = false
        binding.excerciseViewPager.setPadding(50, 0, 50, 0)
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

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }
}