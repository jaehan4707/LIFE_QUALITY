package com.example.myapplication.question

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.SplashActivity.Companion.type
import com.example.myapplication.question.QuestionMainpage.Companion.Id
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.R
import com.example.myapplication.databinding.Type3FragmentBinding
import com.example.myapplication.viewModel.RadioViewModel
import java.util.Collections

class Fragment3 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    var checkCount : Int =0
    private val sharedViewModel: RadioViewModel by lazy {
        ViewModelProvider(this).get(RadioViewModel::class.java)
    }
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
        Log.d("problem","fragment3 : ${tempSurvey.type.toInt()}")

        for ((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }
        for(i in 0 until keyList.size){ //벨류 값이 작은대로 스왑.
            for(j in i+1 until keyList.size){
                if(keyList[i]>keyList[j]){
                    Collections.swap(keyList,i,j)
                    Collections.swap(valueList,i,j)
                }
            }
        }
        if(tempSurvey.type.toInt()!=6) {
            Log.d("problem","라디오 레이아웃")
            binding.radioLayout.visibility =View.VISIBLE
            binding.checkboxLayout.visibility=View.GONE
            binding.rb1.text = valueList.get(0)
            binding.rb2.text = valueList.get(1)
            binding.rb3.text = valueList.get(2)
            var view = inflater.inflate(R.layout.type3_fragment, container, false)
            group = binding.groupF3
            group.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    binding.rb1.id -> Id = (keyList[0].toDouble())
                    binding.rb2.id -> Id = (keyList[1].toDouble())
                    binding.rb3.id -> Id = (keyList[2].toDouble())
                }
            }
            return binding.root
        }
        else{
            Log.d("problem","체크박스 레이아웃")
            binding.radioLayout.visibility =View.GONE
            binding.checkboxLayout.visibility=View.VISIBLE

            binding.checkBox1.text = valueList[0]
            binding.checkBox2.text = valueList[1] //여기 수정
            binding.checkBox3.text = valueList[2]
            sum_checkbox(binding.checkBox1)
            sum_checkbox(binding.checkBox2)
            sum_checkbox(binding.checkBox3)
            Id = when (checkCount) {
                3 -> 2.0
                2 ->1.0
                else -> 0.0
            }
            return binding.root
        }
    }

    fun sum_checkbox(checkBox: CheckBox){
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkCount++
            } else {
                checkCount--
            }
            Log.d("problem", "체크된 체크박스 개수: $checkCount")
        }
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
}