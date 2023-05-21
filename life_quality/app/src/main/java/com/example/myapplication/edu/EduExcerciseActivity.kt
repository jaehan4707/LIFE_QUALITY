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


        var adapter = EduAdapter(excercise_models, this)
        /*
        adapter.setBackBussttonListener(object:EduAdapter.BackButtonListenr{
            override fun onBackPressed() {
                Log.d("test","뒤로가기를 눌렀스빈다")
                if(adapter.isImageExpanded){ //뒤로가기를 누르고 확장된 상태라면.
                    Log.d("test","뒤로가기 눌렀고, 확장된 상태라 이미지 원상복구")
                    adapter.collapseImage() //원상복구
                }
                else{
                    Log.d("test","뒤로가기를 눌럿지만 이미지 확대는 안된 상태")
                }

            }
        })
        */
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