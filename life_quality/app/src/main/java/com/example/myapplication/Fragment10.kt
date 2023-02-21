package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.QuestionMainpage.Companion.curCount
import com.example.myapplication.QuestionMainpage.Companion.group
import com.example.myapplication.QuestionMainpage.Companion.keyList
import com.example.myapplication.databinding.Type10FragmentBinding

class Fragment10 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.


        var valueList = mutableListOf<String>()
        var binding = Type10FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        Log.d("this is type10 curCountList", "curCount is : $curCount")
        binding.type10Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type10Title.text = QuestionMainpage.tempSurvey.title.toString()
        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

        binding.rb1.text = valueList[0]
        binding.rb2.text = valueList[1]
        binding.rb3.text = valueList[2]
        binding.rb4.text = valueList[3]
        binding.rb5.text = valueList[4]
        binding.rb6.text = valueList[5]
        binding.rb7.text = valueList[6]
        binding.rb8.text = valueList[7]
        binding.rb9.text= valueList[8]
        binding.rb10.text= valueList[9]
        var view = inflater.inflate(R.layout.type10_fragment, container, false)
        group = binding.groupF10
        group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                binding.rb1.id -> {
                    QuestionMainpage.Id = (keyList[0].toInt())
                }
                binding.rb2.id -> {
                    QuestionMainpage.Id = (keyList[1].toInt())
                }
                binding.rb3.id -> {
                    QuestionMainpage.Id = (keyList[2].toInt())
                }
                binding.rb4.id -> {
                    QuestionMainpage.Id = (keyList[3].toInt())
                }
                binding.rb5.id -> {
                    QuestionMainpage.Id = (keyList[4].toInt())
                }
                binding.rb6.id->{
                    QuestionMainpage.Id = (keyList[5].toInt())
                }
                binding.rb7.id->{
                    QuestionMainpage.Id = (keyList[6].toInt())
                }
                binding.rb8.id->{
                    QuestionMainpage.Id = (keyList[7].toInt())
                }
                binding.rb9.id->{
                    QuestionMainpage.Id = (keyList[8].toInt())
                }
                binding.rb10.id->{
                    QuestionMainpage.Id = (keyList[9].toInt())
                }
            }
        }
        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment10.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}