package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.QuestionMainpage.Companion.group
import com.example.myapplication.QuestionMainpage.Companion.keyList
import com.example.myapplication.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.databinding.DialogStartBinding
import com.example.myapplication.databinding.Smoke1DialogBinding
import com.example.myapplication.databinding.Smoke2DialogBinding
import com.example.myapplication.databinding.Smoke3DialogBinding
import com.example.myapplication.databinding.Type4FragmentBinding

class Fragment4 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.
        var valueList = mutableListOf<String>()
        var binding = Type4FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type4Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type4Title.text = QuestionMainpage.tempSurvey.title.toString()

        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

        binding.type4Answer1.text = valueList.get(0)
        binding.type4Answer2.text = valueList.get(1)
        binding.type4Answer3.text = valueList.get(2)
        binding.type4Answer4.text = valueList.get(3)
        Log.d("test","4번쨰 프래그먼트")
        var view = inflater.inflate(R.layout.type4_fragment, container, false)
        group = binding.groupF4
        group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                binding.rb1.id -> {
                    QuestionMainpage.Id = (keyList[0].toInt())
                    if(tempSurvey.type.toInt() == 4) {
                        //다이얼로그

                    }

                }
                binding.rb2.id -> {
                    QuestionMainpage.Id = (keyList[1].toInt())
                    if(tempSurvey.type.toInt() == 4) {
                        //다이얼로그
                        showDialog1()
                    }
                }
                binding.rb3.id -> {
                    QuestionMainpage.Id = (keyList[2].toInt())
                    if(tempSurvey.type.toInt() == 4) {
                        //다이얼로그
                        showDialog2()
                    }
                }
                binding.rb4.id -> {
                    QuestionMainpage.Id = (keyList[3].toInt())
                    if(tempSurvey.type.toInt() == 4) {
                        //다이얼로그
                        showDialog3()
                    }
                }
            }
        }
        return binding.root

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment4.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    fun showDialog1() {
        var dialogBinding = Smoke1DialogBinding.inflate(layoutInflater)
        var dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)


        dialogBinding.smoke1Start.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            dialog?.dismiss()
        }
        dialogBinding.smoke1End.setOnClickListener() {
            dialog?.dismiss()
        }

        dialog?.show()
        dialog?.window?.setLayout(1000, 1000)
    }
    fun showDialog2() {
        var dialogBinding = Smoke2DialogBinding.inflate(layoutInflater)
        var dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)


        dialogBinding.smoke2Start.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            dialog?.dismiss()
        }
        dialogBinding.smoke2End.setOnClickListener() {
            dialog?.dismiss()
        }

        dialog?.show()
        dialog?.window?.setLayout(1000, 1000)
    }
    fun showDialog3() {
        var dialogBinding = Smoke3DialogBinding.inflate(layoutInflater)
        var dialog = this?.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)


        dialogBinding.smoke3Start.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            dialog?.dismiss()
        }
        dialogBinding.smoke3End.setOnClickListener() {
            dialog?.dismiss()
        }

        dialog?.show()
        dialog?.window?.setLayout(1000, 1000)
    }
}