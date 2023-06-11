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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.question.QuestionMainpage.Companion.Id
import com.example.myapplication.databinding.NotypeFragmentBinding
import com.example.myapplication.model.Bmi
import com.example.myapplication.model.Time
import com.example.myapplication.question.QuestionMainpage
import com.example.myapplication.question.QuestionMainpage.Companion.curCount
import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.viewModel.QuestionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotypeFragment : Fragment() {

    private lateinit var sharedViewModel : QuestionViewModel
    private var height = 0.0
    private var weight = 0.0
    var time = 0
    var min = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel=ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)
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

        binding.notypeNumber.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.notypeTitle.text = QuestionMainpage.tempSurvey.title
        Log.d("test", "입력형 프래그먼트에 왔습니다")
        when (QuestionMainpage.tempSurvey.type.toInt()) {
            1 -> { //1이면 시간 레이아웃.
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.VISIBLE
                binding.BmiLayout.visibility = View.GONE
                val time : Time = sharedViewModel.getTimeMap(curCount)
                Log.d("viewModel", "${time.hour}, ${time.min}")
                if(time.hour!=-1 && time.min !=-1){ //값이 있다면.
                    Log.d("viewModel","타임 레이아웃")
                    binding.notypehour.setText(time.hour.toString())
                    binding.notypemin.setText(time.min.toString())
                    Id=time.calTime()
                }
            }
            2 -> { //2면 단순 횟수 입력 레이아웃
                binding.numberlayout.visibility = View.VISIBLE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility = View.GONE
                val temp = sharedViewModel.getNumberMap(curCount)
                if(temp!=-1){
                    binding.notypeAnswer.setText(temp.toString())
                    Id=temp.toDouble()
                }
            }
            3 -> { //키와 몸무게 레이아웃
                Log.d("test", "type은 3")
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility = View.VISIBLE
                val bmi : Bmi = sharedViewModel.getBmiMap(curCount)
                Log.d("problem","몸무게 뷰모델 : ${bmi.returnBmi()}")
                if(bmi.height!=0.0 && bmi.weight!=0.0){
                    binding.height.setText(bmi.height.toInt().toString())
                    binding.weight.setText(bmi.weight.toInt().toString())
                    Id=bmi.calBmi()
                    Log.d("viewModel","bmi : ${Id}")
                }
            }
            /*
            4 -> {
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility = View.GONE
            }
             */
        }
        runBlocking {
            val job = CoroutineScope(Dispatchers.IO).launch {
                if (binding.numberlayout.visibility == View.VISIBLE) { //횟수 레이아웃이 활성화일때
                    binding.notypeAnswer.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.notypeAnswer)
                            var edit_id = try {
                                trim_text.toInt()
                            } catch (e: java.lang.Exception) {
                                Log.d("problem: ", "오류발생")
                            }
                            Id = edit_id.toDouble()
                            sharedViewModel.setNumberMap(curCount, Id.toInt())
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
                            if (inputText.isEmpty()) { // 입력된 텍스트가 없는 경우
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) { // 입력된 텍스트가 숫자인 경우 // 처리 로직 추가
                                    Id=inputText.toDouble()
                                } else {  // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기
                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                    sharedViewModel.setNumberMap(curCount,Id.toInt())
                }

                if (binding.timeLayout.visibility == View.VISIBLE) {
                    binding.notypehour.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}
                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.notypehour)
                            time = try {
                                trim_text.toInt() * 60
                            } catch (e: java.lang.Exception) {
                                Log.d("problem", "오류 발생")
                            }
                            sharedViewModel.setTimeMap(curCount,time/60,min) //연결하기.
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
                            if (inputText.isEmpty()) { // 입력된 텍스트가 없는 경우 // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) { // 입력된 텍스트가 숫자인 경우 // 처리 로직 추가
                                    Id=inputText.toDouble()*60
                                    sharedViewModel.setTimeMap(curCount,time/60,min) //연결하기.
                                } else { // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기
                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                    binding.notypemin.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.notypemin)
                            min = try {
                                trim_text.toInt()
                            } catch (e: java.lang.Exception) {
                                Log.d("problem", "오류 발생")
                            }
                            Id = (time + min).toDouble()
                            sharedViewModel.setTimeMap(curCount,time/60,min) //연결하기.
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
                            if (inputText.isEmpty()) { // 입력된 텍스트가 없는 경우 // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) { // 입력된 텍스트가 숫자인 경우// 처리 로직 추가
                                    Id=time+inputText.toDouble()
                                    sharedViewModel.setTimeMap(curCount,time/60,min) //연결하기.
                                } else {  // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기
                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }

                }

                if (binding.BmiLayout.visibility == View.VISIBLE) { //횟수 레이아웃이 활성화일때
                    binding.height.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.height)
                            height = try {
                                (trim_text.toDouble() / 100.0)
                            } catch (e: java.lang.Exception){
                                Log.d("problem", "예외발생")
                            }.toDouble()
                            sharedViewModel.setBmiMap(curCount,height, weight)
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
                            if (inputText.isEmpty()) { // 입력된 텍스트가 없는 경우 // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) { // 입력된 텍스트가 숫자인 경우 // 처리 로직 추가
                                    Id=inputText.toDouble()/100.0
                                    sharedViewModel.setBmiMap(curCount,height, weight)
                                } else { // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기
                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }
                    binding.weight.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                        override fun afterTextChanged(p0: Editable?) {
                            var trim_text = delete_blank(p0, binding.weight)
                            weight = try {
                                trim_text.toDouble()
                            } catch (e: java.lang.Exception){
                                Log.d("problem : ", "예외발생")
                            }.toDouble()
                            sharedViewModel.setBmiMap(curCount,height, weight)
                            //Id = (weight / (height * height)).toDouble() //BMI\
                            Id = sharedViewModel.getBmiMap(curCount).calBmi()
                            Log.d("viewModel"," ${curCount}, ${sharedViewModel.getBmiMap(curCount).height},${sharedViewModel.getBmiMap(curCount).weight}")

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
                            if (inputText.isEmpty()) { // 입력된 텍스트가 없는 경우 // 처리 로직 추가
                                Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                val isNumber = inputText.toIntOrNull() != null // 입력된 텍스트가 숫자인지 확인
                                if (isNumber) { // 입력된 텍스트가 숫자인 경우 // 처리 로직 추가
                                    //Id=inputText.toDouble() / (height*height)
                                    sharedViewModel.setBmiMap(curCount,height, weight)
                                    Id=sharedViewModel.getBmiMap(curCount).calBmi()
                                } else { // 입력된 텍스트가 숫자가 아닌 경우
                                    binding.notypeAnswer.text.clear()
                                    Toast.makeText(requireContext(), "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.hideSoftInputFromWindow(binding.notypeAnswer.windowToken, 0) // 키보드 숨기기
                            true // 이벤트 처리 완료
                        } else {
                            false // 이벤트 처리 안 함
                        }
                    }

                }
            }
            job.join() //job이 끝날떄까지 대기함.
            Log.d("viewModel", "ID : ${Id}")
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

