package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityEduSleepBinding
import com.example.myapplication.databinding.DialogStartBinding
import com.example.myapplication.databinding.EduDialogBinding
import com.example.myapplication.databinding.EduMouthBinding
import com.example.myapplication.question.QuestionSelect

class EduSleepActivity : AppCompatActivity() {
    var sleep_models = mutableListOf<Int>()
    var sleep_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityEduSleepBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sleep_models.add(R.drawable.edu_sleep_01)
        sleep_models.add(R.drawable.edu_sleep_02)
        sleep_models.add(R.drawable.edu_sleep_03)
        sleep_models.add(R.drawable.edu_sleep_04)
        sleep_models.add(R.drawable.edu_sleep_05)
        sleep_models.add(R.drawable.edu_sleep_06)
        sleep_models.add(R.drawable.edu_sleep_07)
        sleep_colors.add(getColor(R.color.gray))
        sleep_colors.add(getColor(R.color.gray))
        sleep_colors.add(getColor(R.color.gray))
        sleep_colors.add(getColor(R.color.gray))
        sleep_colors.add(getColor(R.color.gray))
        sleep_colors.add(getColor(R.color.gray))
        sleep_colors.add(getColor(R.color.gray))


        var adapter = EduAdapter(sleep_models, this)
        binding.sleepViewPager.adapter = adapter
        binding.maxPage.text = adapter.count.toString()
        binding.sleepViewPager.clipToPadding = false
        binding.sleepViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position < adapter.count - 1 && position < sleep_colors.size - 1) {
                    binding.sleepViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            sleep_colors[position],
                            sleep_colors[position + 1]
                        ) as Int
                    )
                } else {
                    binding.sleepViewPager.setBackgroundColor(sleep_colors[sleep_colors.size - 1])
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