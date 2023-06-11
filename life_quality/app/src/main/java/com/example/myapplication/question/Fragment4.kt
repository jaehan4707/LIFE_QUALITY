package com.example.myapplication.question

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.R
import com.example.myapplication.databinding.AgreeDialogBinding
import com.example.myapplication.databinding.QuestionDialogBinding
import com.example.myapplication.databinding.Type4FragmentBinding
import com.example.myapplication.question.QuestionMainpage.Companion.Id
import com.example.myapplication.question.QuestionMainpage.Companion.curCount
import com.example.myapplication.viewModel.QuestionViewModel
import java.util.Collections

class Fragment4 : Fragment() {
    lateinit var callback: OnBackPressedCallback

    private lateinit var  sharedViewModel : QuestionViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedViewModel = ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)
        var keyList= mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type4FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type4Number.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.type4Title.text = tempSurvey.title
        for((key, value) in tempSurvey.answer) {
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
        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        binding.rb4.text = valueList.get(3)
        if(tempSurvey.type.toInt()==4) { //수면습과 j일때
            Log.d("problem","check")
            group = binding.groupF4
            group.setOnCheckedChangeListener { radioGroup, i ->
                sharedViewModel.setRadioButton(curCount,i)
                Log.d("problem","check")
                when(i){
                    binding.rb1.id->Id= (keyList[0].toDouble())
                    binding.rb2.id->{
                        Id=(keyList[1].toDouble())
                        showDialog()
                    }
                    binding.rb3.id->{
                        Id=keyList[2].toDouble()
                        showDialog()
                    }
                    binding.rb4.id->{
                        Id=keyList[3].toDouble()
                        showDialog()
                    }
                }
            }
        }
        //Log.d("test","4번쨰 프래그먼트")
        else {
            group = binding.groupF4
            group.setOnCheckedChangeListener { radioGroup, i ->
                sharedViewModel.setRadioButton(curCount, i)
                when (i) {
                    binding.rb1.id -> Id = (keyList[0].toDouble())
                    binding.rb2.id -> Id = (keyList[1].toDouble())
                    binding.rb3.id -> Id = (keyList[2].toDouble())
                    binding.rb4.id -> Id = (keyList[3].toDouble())
                }
            }
            val selectedRadioButtonId = sharedViewModel.getRadioButton(curCount)
            Log.d("problem", "라디오체크할래 ${selectedRadioButtonId}")
            if (selectedRadioButtonId != -1) {
                group.check(selectedRadioButtonId)
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
    fun showDialog(){
        var dialogBinding = QuestionDialogBinding.inflate(layoutInflater)
        var dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)

        dialogBinding.finish.setOnClickListener{ //닫기버튼
            val result = dialogBinding.searchText.text.toString() //결과
            dialog?.dismiss()
        }
        dialogBinding.finish.setOnClickListener { //완료버튼
            dialog?.dismiss()
        }
        dialog?.show()
        dialogBinding.close
        dialog?.window?.setLayout(1000, 1000)
    }
}