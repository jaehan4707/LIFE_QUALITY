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
import android.widget.EditText
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
    private fun delete_blank(p0: Editable?, edit: EditText): String {
        var str1 = p0.toString()
        var str2 = str1.trim()
        if (str1 != str2) {
            edit.setText(str2)
            edit.setSelection(str2.length)
        }
        return str2
    }
    fun closeDialog(rootView: View){
        rootView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // 터치 이벤트가 발생하면 키보드를 숨깁니다.
                Log.d("test","터치이벤트가 발생해서 키보드르 숨깁니다.")
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(rootView.windowToken, 0)
                rootView.clearFocus()
            }
            false
        }
    }
    fun showDialog() {
        var dialogBinding = Drink1DialogBinding.inflate(layoutInflater)
        var dialog = this.context?.let { Dialog(it) }
        val rootView = dialogBinding.root
        closeDialog(rootView)

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogBinding.root)
        dialog?.setCancelable(false)
        var age=0
        dialogBinding.editDrink.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }
            override fun afterTextChanged(p0: Editable?) {

                var trim_text = delete_blank(p0,dialogBinding.editDrink)
                age= try {
                    (trim_text.toInt())
                } catch (e: java.lang.Exception){
                    Log.d("problem", "예외발생")
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //위의 주석은 enter 누를 시 바로 닫기.
                var number :Int? = p0.toString().toIntOrNull()
                Log.d("test","edit_text ${p0}")
                if(number==null){ //숫자가 아니다.
                    dialogBinding.editDrink.text.clear()
                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        })
        dialogBinding.drink1Start.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            if(dialogBinding.editDrink.toString().toIntOrNull()!=null)
            {
                dialog?.dismiss()
            }
            else{
                Toast.makeText(requireContext(), "값을 입력해주세요!!", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBinding.drink1End.setOnClickListener() {
            dialog?.dismiss()
        }

        dialog?.show()
        dialog?.window?.setLayout(1000, 1000)
    }
}