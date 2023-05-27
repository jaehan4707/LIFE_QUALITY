package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.myapplication.question.QuestionMainpage.Companion.Id
import com.example.myapplication.databinding.NotypeFragmentBinding
import com.example.myapplication.databinding.QuestionMainpageBinding
import com.example.myapplication.question.QuestionMainpage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotypeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.

        var valueList = mutableListOf<String>()
        var binding = NotypeFragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        val rootView = binding.root

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
        var binding2 = QuestionMainpageBinding.inflate(layoutInflater)

        binding.notypeNumber.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.notypeTitle.text = QuestionMainpage.tempSurvey.title.toString()
        Log.d("test", "입력형 프래그먼트에 왔습니다")
        when (QuestionMainpage.tempSurvey.type.toInt()) {
            1 -> {
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.VISIBLE
                binding.BmiLayout.visibility = View.GONE
            } //1이면 시간 레이아웃.
            2 -> {
                binding.numberlayout.visibility = View.VISIBLE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility = View.GONE
            }
            3 -> {
                Log.d("test", "type은 3")
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility = View.VISIBLE
            }
            4 -> {
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility = View.GONE
                //addres layout visibile
            }
        }
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).launch {
                if (binding.numberlayout.visibility == View.VISIBLE) { //횟수 레이아웃이 활성화일때
                    binding.notypeAnswer.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.notypeAnswer)
                            var edit_id = try {
                                trim_text.toInt()
                            } catch (e: java.lang.Exception) {
                                Log.d("problem: ", "오류발생")
                            }
                            Id = edit_id.toDouble()
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            var number :Int? = p0.toString().toIntOrNull()
                            Log.d("test","edit_text ${p0}")
                            if(number==null){ //숫자가 아니다.
                                binding.notypeAnswer.text.clear()
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    binding.notypeAnswer.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                            // Enter(엔터) 키를 누른 경우 또는 완료(완료) 액션을 수행한 경우
                            Log.d("problem","엔터키")
                            val inputText = binding.notypeAnswer.text.toString().trim() // 입력된 텍스트 가져오기 (앞뒤 공백 제거)
                            if (inputText.isEmpty()) {
                                // 입력된 텍스트가 없는 경우
                                // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) {
                                    // 입력된 텍스트가 숫자인 경우
                                    // 처리 로직 추가
                                    Id=inputText.toDouble()
                                } else {
                                    // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }

                            val inputMethodManager =
                                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기

                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                }
                var time = 0
                var min = 0
                if (binding.timeLayout.visibility == View.VISIBLE) {
                    binding.notypehour.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.notypehour)
                            time = try {
                                trim_text.toInt() * 60
                            } catch (e: java.lang.Exception) {
                                Log.d("problem", "오류 발생")
                            }
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            var number :Int? = p0.toString().toIntOrNull()
                            Log.d("test","edit_text ${p0}")
                            if(number==null){ //숫자가 아니다.
                                binding.notypehour.text.clear()
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    binding.notypehour.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                            // Enter(엔터) 키를 누른 경우 또는 완료(완료) 액션을 수행한 경우
                            Log.d("problem","엔터키")
                            val inputText = binding.notypehour.text.toString().trim() // 입력된 텍스트 가져오기 (앞뒤 공백 제거)
                            if (inputText.isEmpty()) {
                                // 입력된 텍스트가 없는 경우
                                // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) {
                                    // 입력된 텍스트가 숫자인 경우
                                    // 처리 로직 추가
                                    Id=inputText.toDouble()*60
                                } else {
                                    // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }

                            val inputMethodManager =
                                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기

                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                    binding.notypemin.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.notypemin)
                            min = try {
                                trim_text.toInt()
                            } catch (e: java.lang.Exception) {
                                Log.d("problem", "오류 발생")
                            }
                            Id = (time + min).toDouble()
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            var number :Int? = p0.toString().toIntOrNull()
                            Log.d("test","edit_text ${p0}")
                            if(number==null){ //숫자가 아니다.
                                binding.notypemin.text.clear()
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    binding.notypemin.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                            // Enter(엔터) 키를 누른 경우 또는 완료(완료) 액션을 수행한 경우
                            Log.d("problem","엔터키")
                            val inputText = binding.notypemin.text.toString().trim() // 입력된 텍스트 가져오기 (앞뒤 공백 제거)
                            if (inputText.isEmpty()) {
                                // 입력된 텍스트가 없는 경우
                                // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) {
                                    // 입력된 텍스트가 숫자인 경우
                                    // 처리 로직 추가
                                    Id=time+inputText.toDouble()
                                } else {
                                    // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }

                            val inputMethodManager =
                                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기

                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                }
                var height = 0.0
                var weight = 0.0
                if (binding.BmiLayout.visibility == View.VISIBLE) { //횟수 레이아웃이 활성화일때
                    binding.height.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun afterTextChanged(p0: Editable?) {

                            var trim_text = delete_blank(p0, binding.height)
                            height = try {
                                (trim_text.toDouble() / 100.0)
                            } catch (e: java.lang.Exception){
                                Log.d("problem", "예외발생")
                            }.toDouble()
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            var number :Int? = p0.toString().toIntOrNull()
                            Log.d("test","edit_text ${p0}")
                            if(number==null){ //숫자가 아니다.
                                binding.height.text.clear()
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    binding.height.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                            // Enter(엔터) 키를 누른 경우 또는 완료(완료) 액션을 수행한 경우
                            Log.d("problem","엔터키")
                            val inputText = binding.height.text.toString().trim() // 입력된 텍스트 가져오기 (앞뒤 공백 제거)
                            if (inputText.isEmpty()) {
                                // 입력된 텍스트가 없는 경우
                                // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) {
                                    // 입력된 텍스트가 숫자인 경우
                                    // 처리 로직 추가
                                    Id=inputText.toDouble()/100.0
                                } else {
                                    // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }

                            val inputMethodManager =
                                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기

                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }

                    binding.weight.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.weight)
                            weight = try {
                                trim_text.toDouble()
                            } catch (e: java.lang.Exception){
                                Log.d("problem : ", "예외발생")
                            }.toDouble()
                            Id = (weight / (height * height)).toDouble() //BMI
                            Log.d(
                                "problem : ",
                                "키 : $height, 몸무게 : $weight, BMI : ${Id.toString()}"
                            )
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            var number :Int? = p0.toString().toIntOrNull()
                            Log.d("test","edit_text ${p0}")
                            if(number==null){ //숫자가 아니다.
                                binding.weight.text.clear()
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    binding.weight.setOnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                            // Enter(엔터) 키를 누른 경우 또는 완료(완료) 액션을 수행한 경우
                            Log.d("problem","엔터키")
                            val inputText = binding.weight.text.toString().trim() // 입력된 텍스트 가져오기 (앞뒤 공백 제거)
                            if (inputText.isEmpty()) {
                                // 입력된 텍스트가 없는 경우
                                // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) {
                                    // 입력된 텍스트가 숫자인 경우
                                    // 처리 로직 추가
                                    Id=inputText.toDouble() / (height*height)
                                } else {
                                    // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }

                            val inputMethodManager =
                                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기

                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                }
            }
            job.join() //job이 끝날떄까지 대기함.
            Log.d("problem", "ID : ${Id}")
        }
        return binding.root
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
}

