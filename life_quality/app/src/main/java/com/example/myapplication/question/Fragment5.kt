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
import com.example.myapplication.question.QuestionMainpage.Companion.Id
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.R
import com.example.myapplication.databinding.Type5FragmentBinding
import com.example.myapplication.viewModel.RadioViewModel

class Fragment5 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    private val sharedViewModel: RadioViewModel by lazy {
        ViewModelProvider(this).get(RadioViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.

        var keyList = mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type5FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
       binding.type5Number.text = "문항 " + QuestionMainpage.curCount.toString()
        //binding.type5Number.text ="문항 " + QuestionMainpage.tempSurvey.id
        binding.type5Title.text = QuestionMainpage.tempSurvey.title.toString()

        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

       // Log.d("test","다섯번째 프래그먼트")
        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        binding.rb4.text = valueList.get(3)
        binding.rb5.text = valueList.get(4)
        var view = inflater.inflate(R.layout.type5_fragment, container, false)
        group = binding.groupF5
        group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                binding.rb1.id -> Id = (keyList[0].toDouble())
                binding.rb2.id -> Id= (keyList[1].toDouble())
                binding.rb3.id -> Id= (keyList[2].toDouble())
                binding.rb4.id -> Id = (keyList[3].toDouble())
                binding.rb5.id ->Id = (keyList[4].toDouble())
            }
        }
        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment5.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}