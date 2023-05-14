package com.example.myapplication.edu

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.EduEmotionBinding
import java.lang.Float.max
import java.lang.Float.min

class EduEmotionActivity : AppCompatActivity() {
    var emotion_models = mutableListOf<Int>()
    var emotion_colors = mutableListOf<Int>()
    var argbEvaluator = ArgbEvaluator()

    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = EduEmotionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emotion_models.add(R.drawable.edu_emotion_1)
        emotion_models.add(R.drawable.edu_emotion_2)
        emotion_models.add(R.drawable.edu_emotion_3)
        emotion_models.add(R.drawable.edu_emotion_4)


        emotion_colors.add(getColor(R.color.edu_emotion_color1))
        emotion_colors.add(getColor(R.color.edu_emotion_color2))
        emotion_colors.add(getColor(R.color.edu_emotion_color3))
        emotion_colors.add(getColor(R.color.edu_emotion_color4))



        //scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        var adapter = EduMouthAdapter(emotion_models, this)
        binding.emotionViewPager.adapter = adapter
        binding.emotionViewPager.clipToPadding = false
        binding.emotionViewPager.setPadding(50, 0, 50, 0)
        binding.emotionViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < adapter.count-1 && position < emotion_colors.size - 1) {
                    binding.emotionViewPager.setBackgroundColor(
                        argbEvaluator.evaluate(
                            positionOffset,
                            emotion_colors[position],
                            emotion_colors[position+1]
                        ) as Int
                    )
                }
                else {
                    binding.emotionViewPager.setBackgroundColor(emotion_colors[emotion_colors.size-1])
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }
}