package com.example.myapplication.question

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.R
import com.example.myapplication.databinding.Type8FragmentBinding
import com.example.myapplication.question.QuestionMainpage.Companion.curCount
import com.example.myapplication.viewModel.RadioViewModel

class Fragment8 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    private lateinit var sharedViewModel: RadioViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel=ViewModelProvider(requireActivity()).get(RadioViewModel::class.java)
        var keyList= mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type8FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type8Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type8Title.text = QuestionMainpage.tempSurvey.title.toString()

        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }
        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        binding.rb4.text = valueList.get(3)
        binding.rb5.text = valueList.get(4)
        binding.rb6.text = valueList.get(5)
        binding.rb7.text = valueList.get(6)
        binding.rb8.text = valueList.get(7)
        var view = inflater.inflate(R.layout.type8_fragment, container, false)
        group = binding.groupF8
        group.setOnCheckedChangeListener { radioGroup, i ->
            sharedViewModel.setRadioButton(curCount,i)
            when(i){
                binding.rb1.id -> QuestionMainpage.Id = (keyList[0].toDouble())
                binding.rb2.id -> QuestionMainpage.Id = (keyList[1].toDouble())
                binding.rb3.id -> QuestionMainpage.Id = (keyList[2].toDouble())
                binding.rb4.id -> QuestionMainpage.Id = (keyList[3].toDouble())
                binding.rb5.id -> QuestionMainpage.Id = (keyList[4].toDouble())
                binding.rb6.id -> QuestionMainpage.Id = (keyList[5].toDouble())
                binding.rb7.id -> QuestionMainpage.Id = (keyList[6].toDouble())
                binding.rb8.id->QuestionMainpage.Id = (keyList[7].toDouble())
            }
        }
        val selectedRadioButtonId = sharedViewModel.getRadioButton(curCount)
        Log.d("problem","라디오체크할래 ${selectedRadioButtonId}")
        if(selectedRadioButtonId !=-1){
            group.check(selectedRadioButtonId)
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