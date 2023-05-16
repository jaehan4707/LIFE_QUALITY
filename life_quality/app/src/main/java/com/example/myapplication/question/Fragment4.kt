package com.example.myapplication.question

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.R
import com.example.myapplication.databinding.Smoke1DialogBinding
import com.example.myapplication.databinding.Smoke2DialogBinding
import com.example.myapplication.databinding.Smoke3DialogBinding
import com.example.myapplication.databinding.Type4FragmentBinding

class Fragment4 : Fragment() {
    lateinit var callback: OnBackPressedCallback
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.

        var keyList= mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type4FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type4Number.text = "문항 " + QuestionMainpage.curCount.toString()
        //binding.type4Number.text ="문항 " + tempSurvey.id
        binding.type4Title.text = QuestionMainpage.tempSurvey.title.toString()

        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        binding.rb4.text = valueList.get(3)
        //Log.d("test","4번쨰 프래그먼트")
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
    private fun delete_blank(p0: Editable?, edit: EditText): String {
        var str1 = p0.toString()
        var str2 = str1.trim()
        if (str1 != str2) {
            edit.setText(str2)
            edit.setSelection(str2.length)
        }
        return str2
    }
    fun showDialog1() {
        var dialogBinding = Smoke1DialogBinding.inflate(layoutInflater)
        var dialog = this.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)

        var num = 0
        dialogBinding.editSmoke1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }
            override fun afterTextChanged(p0: Editable?) {

                var trim_text = delete_blank(p0,dialogBinding.editSmoke1)
                num= try {
                    (trim_text.toInt())
                } catch (e: java.lang.Exception){
                    Log.d("problem", "예외발생")
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var number :Int? = p0.toString().toIntOrNull()
                Log.d("test","edit_text ${p0}")
                if(number==null){ //숫자가 아니다.
                    dialogBinding.editSmoke1.text.clear()
                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        })
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
        var day_num=0
        var month_num=0
        val textWatcher = object  : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                val inputText= p0.toString()
                if(inputText.isNotEmpty() && !inputText.isDigitsOnly()){
                    Log.d("test","숫자를 입력해주세요")
                    p0?.clear()
                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dialogBinding.editDaySmoke.addTextChangedListener(textWatcher)
        dialogBinding.editMonthSmoke.addTextChangedListener(textWatcher)


        dialogBinding.smoke2Start.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            if(dialogBinding.editDaySmoke.text.toString()!="" && dialogBinding.editMonthSmoke.text.toString()!="")
                dialog?.dismiss()
            else
                Toast.makeText(requireContext(), "빈칸이 있어요!", Toast.LENGTH_SHORT).show()
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
        val textWatcher = object  : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                val inputText= p0.toString()
                if(inputText.isNotEmpty() && !inputText.isDigitsOnly()){
                    Log.d("test","숫자를 입력해주세요")
                    p0?.clear()
                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dialogBinding.editPastYear.addTextChangedListener(textWatcher)
        dialogBinding.editPastMonth.addTextChangedListener(textWatcher)
        dialogBinding.editNosmokeYear.addTextChangedListener(textWatcher)
        dialogBinding.editNosmokeMonth.addTextChangedListener(textWatcher)
        dialogBinding.editDayAverageSmoke.addTextChangedListener(textWatcher)

        dialogBinding.smoke3Start.setOnClickListener() { //확인하기.
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            if(dialogBinding.editNosmokeMonth.text.toString()!="" && dialogBinding.editNosmokeYear.text.toString()!=""&&
                    dialogBinding.editPastMonth.text.toString()!=""&&dialogBinding.editPastYear.text.toString()!=""
                && dialogBinding.editDayAverageSmoke.text.toString()==""){
                dialog?.dismiss()
            }
            else{
                Toast.makeText(requireContext(), "빈칸이 있어요!", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBinding.smoke3End.setOnClickListener() {
            dialog?.dismiss()
        }

        dialog?.show()
        dialog?.window?.setLayout(1000, 1500)
    }
    fun Textchanged(edit: EditText){
        var number :Int? = edit.toString().toIntOrNull()
        if(number==null){ //숫자가 아니다.
            edit.text.clear()
            Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
        }
    }
    fun afterchanged(edit : EditText,p0: Editable?){

        var trim_text = delete_blank(p0,edit)
        Log.d("test","흡연 레이아웃 ${trim_text},${p0}")
        var num=0
        num= try {
            (trim_text.toInt())
        } catch (e: java.lang.Exception){
            Log.d("problem", "예외발생")
        }
    }
}