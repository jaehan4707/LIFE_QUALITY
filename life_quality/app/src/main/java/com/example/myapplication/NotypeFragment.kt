package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.myapplication.QuestionMainpage.Companion.Id
import com.example.myapplication.QuestionMainpage.Companion.keyList
import com.example.myapplication.databinding.NotypeFragmentBinding
import com.example.myapplication.databinding.QuestionMainpageBinding
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
        var binding2 = QuestionMainpageBinding.inflate(layoutInflater)
        binding.notypeNumber.text = "문항 " + QuestionMainpage.curCount.toString()
        binding.notypeTitle.text = QuestionMainpage.tempSurvey.title.toString()
        Log.d("test", "입력형 프래그먼트에 왔습니다")
        when (QuestionMainpage.tempSurvey.type.toInt()) {
            1 -> {
                binding.numberlayout.visibility = View.GONE
                binding.timeLayout.visibility = View.VISIBLE
                binding.BmiLayout.visibility=View.GONE
            } //1이면 시간 레이아웃.
            2 -> {
                binding.numberlayout.visibility = View.VISIBLE
                binding.timeLayout.visibility = View.GONE
                binding.BmiLayout.visibility=View.GONE
            }
            3 -> {
                Log.d("test","type은 3")
                binding.numberlayout.visibility=View.GONE
                binding.timeLayout.visibility=View.GONE
                binding.BmiLayout.visibility=View.VISIBLE
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
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            Id = p0.toString().toInt()
                            Log.d("test", "${Id}")
                        }
                    })
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

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            time = p0.toString().toInt()*60
                            Id = time
                            Log.d("시간 입력", "${Id}")
                        }
                    })
                    binding.notypemin.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun afterTextChanged(p0: Editable?) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            min = p0.toString().toInt()
                            Id = time+min
                            Log.d("분 입력", "${Id}")
                        }
                    })
                }
                var height = 0.0
                var weight =0
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
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            height=p0.toString().toDouble()/100 //M로 변환
                        }
                    })
                    binding.weight.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }
                        override fun afterTextChanged(p0: Editable?) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            Log.d("test","몸무게 : ${p0}")
                            Id=(p0.toString().toInt()/(height*height)).toInt() //몸무게 / 키의제곱(m)
                            //p0 몸무게
                        }
                    })
                }
            }
            job.join() //job이 끝날떄까지 대기함.
            Log.d("test","ID : ${Id}")
        }
        return binding.root
    }
}
//여기서 Id값이 안바뀌고 넘어감.

