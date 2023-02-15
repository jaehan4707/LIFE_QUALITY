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
import com.example.myapplication.QuestionMainpage.Companion.group
import com.example.myapplication.QuestionMainpage.Companion.keyList
import com.example.myapplication.databinding.Type8FragmentBinding

class Fragment8 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.


        var valueList = mutableListOf<String>()
        var binding = Type8FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type8Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type8Title.text = QuestionMainpage.tempSurvey.title.toString()

        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

        binding.type8Answer1.text = valueList.get(0)
        binding.type8Answer2.text = valueList.get(1)
        binding.type8Answer3.text = valueList.get(2)
        binding.type8Answer4.text = valueList.get(3)
        binding.type8Answer5.text = valueList.get(4)
        binding.type8Answer6.text = valueList.get(5)
        binding.type8Answer7.text = valueList.get(6)
        binding.type8Answer7.text = valueList.get(7)
        var view = inflater.inflate(R.layout.type8_fragment, container, false)
        group = binding.groupF8
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
            }
        }
        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment8.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}