package com.example.myapplication

import android.R.*
import android.animation.ArgbEvaluator
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.EducationActivityBinding


class CardActivity : AppCompatActivity() {
    var models = mutableListOf<Model>()
    var colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EducationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        models.add(Model(R.drawable.nutrition, "영양관리", "나이가 들수록 떨어지는 입맛!\n 건강한 식생활 함께 지켜봐요"))
        models.add(Model(R.drawable.emotion, "우울관리", "혹시 내가 노인 우울증?\n 예방과 치료방법 살펴봐요"))
        models.add(Model(R.drawable.mouth, "구강관리", "자신감 있는 미소를 위해! \n깨끗하게 관리하고 밝게 웃어봐요^^"))
        models.add(Model(R.drawable.excercise, "운동 건강관리", "자신에게 맞는 올바른 운동 실천, \n안전하게 건강 챙겨봐요!"))
        models.add(Model(R.drawable.yosil, "요실금 관리", "말 못 할 고민, \n골반저근을 통해 예방해요!"))
        models.add(Model(R.drawable.yosil, "요실금 관리", "말 못 할 고민, \n방관훈련을 통해 예방해봐요!"))

        var colors_temp = mutableListOf<Int>(getColor(R.color.color1), getColor(R.color.color2), getColor(R.color.color3), getColor(R.color.color4), getColor(R.color.color5),getColor(R.color.color5))
        colors = colors_temp


        var adapter = CardAdapter(models, this)
        binding.viewPager.adapter = adapter
        binding.viewPager.clipToPadding = false
        binding.viewPager.setPadding(130, 0, 130, 0)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < adapter.count-1 && position < colors.size - 1) {
                    binding.viewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            colors[position],
                            colors[position+1]
                        ) as Int
                    )
                }
                else {
                    binding.viewPager.setBackgroundColor(colors[colors.size-1])
                }
            }


            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }
}


