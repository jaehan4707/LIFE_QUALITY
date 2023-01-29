package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.Type10FragmentBinding

class NotypeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.
        var keyList = mutableListOf<String>()
        var valueList = mutableListOf<String>()
        var binding = Type10FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        binding.type10Number.text = "문항" + QuestionMainpage.curNumber.toString()
        binding.type10Title.text = QuestionMainpage.tempSurvey.title.toString()
//        Log.d("tempSurvey !!!!", "${QuestionMainpage.tempSurvey.answer.values}")
        for((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
        }

        var view = inflater.inflate(R.layout.notype_fragment, container, false)
        return binding.root
    }
}