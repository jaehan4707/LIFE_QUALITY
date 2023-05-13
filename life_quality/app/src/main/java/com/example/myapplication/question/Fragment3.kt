package com.example.myapplication.question

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.question.QuestionMainpage.Companion.Id
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.R
import com.example.myapplication.databinding.Drink1DialogBinding
import com.example.myapplication.databinding.Type3FragmentBinding

class Fragment3 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.



        var keyList= mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type3FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type3Number.text = "문항 " + QuestionMainpage.curCount.toString()
        //binding.type3Number.text = "문항 " + tempSurvey.id
        binding.type3Title.text = QuestionMainpage.tempSurvey.title.toString()

        for ((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        var view = inflater.inflate(R.layout.type3_fragment, container, false)
        group = binding.groupF3
        group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                binding.rb1.id -> {
                    Id = (keyList[0].toInt())
                }
                binding.rb2.id -> {
                    Id = (keyList[1].toInt())
                    if(tempSurvey.surveyType=="Drink")
                        showDialog()
                }
                binding.rb3.id -> {
                    Id = (keyList[2].toInt())
                }
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment3.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    fun showDialog() {
        var dialogBinding = Drink1DialogBinding.inflate(layoutInflater)
        var dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)


        dialogBinding.drink1Start.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            dialog?.dismiss()
        }
        dialogBinding.drink1End.setOnClickListener() {
            dialog?.dismiss()
        }

        dialog?.show()
        dialog?.window?.setLayout(1000, 1000)
    }
}