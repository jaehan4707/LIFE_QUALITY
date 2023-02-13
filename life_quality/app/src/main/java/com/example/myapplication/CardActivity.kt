package com.example.myapplication

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.EducationActivityBinding
import com.example.myapplication.databinding.ItemBinding
import com.google.android.material.snackbar.Snackbar

class CardActivity : AppCompatActivity() {
    var models = mutableListOf<Model>()
    var colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EducationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        models.add(Model(R.drawable.nutrition, "Nutrition", "asdasdlakwdjiajdlkwjdialsdkjwidlaskjdiflaknlkcnalkjdiwldkasjdildkjqwidjlqksjdlqk"))
        models.add(Model(R.drawable.emotion, "Emotion", "asdasdlakwdjiajdlkwjdialsdkjwidlaskjdiflaknlkcnalkjdiwldkasjdildkjqwidjlqksjdlqk"))
        models.add(Model(R.drawable.mouth, "Mouth", "asdasdlakwdjiajdlkwjdialsdkjwidlaskjdiflaknlkcnalkjdiwldkasjdildkjqwidjlqksjdlqk"))
        models.add(Model(R.drawable.excercise, "Excercise", "asdasdlakwdjiajdlkwjdialsdkjwidlaskjdiflaknlkcnalkjdiwldkasjdildkjqwidjlqksjdlqk"))
        models.add(Model(R.drawable.yosil, "Excercise", "asdasdlakwdjiajdlkwjdialsdkjwidlaskjdiflaknlkcnalkjdiwldkasjdildkjqwidjlqksjdlqk"))


        var colors_temp = mutableListOf<Int>(getColor(R.color.color1), getColor(R.color.color2), getColor(R.color.color3), getColor(R.color.color4), getColor(R.color.color5))

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


