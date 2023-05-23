package com.example.myapplication.question

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.question.QuestionMainpage.Companion.curCount
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.databinding.Type2FragmentBinding

class Fragment2 : Fragment() {

    lateinit var callback : OnBackPressedCallback
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val keyList = ArrayList<String>(2) // 초기 용량(capacity)이 2인 ArrayList 생성
        val valueList = ArrayList<String>(2) // 초기 용량(capacity)이 2인 ArrayList 생성
        var binding = Type2FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type2Number.text = "문항 " + curCount.toString()
        binding.type2Title.text = tempSurvey.title.toString()
        var keyIndex = -1
        for ((key, value) in tempSurvey.answer) {
            if (value == "예") {
                keyList.add(0, key)
                valueList.add(0, value)
            } else if (value == "아니오") {
                if (keyIndex == -1) {
                    keyList.add(key)
                    valueList.add(value)
                    keyIndex = 1
                } else {
                    keyList.add(keyIndex, key)
                    valueList.add(keyIndex, value)
                    keyIndex++
                }
            } else {
                keyList.add(key)
                valueList.add(value)
            }
        }
        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        group = binding.groupF2
        group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                binding.rb1.id -> {
                    QuestionMainpage.Id = (keyList[0].toInt())
                }
                binding.rb2.id -> {
                    QuestionMainpage.Id = (keyList[1].toInt())
                }
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment2.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
