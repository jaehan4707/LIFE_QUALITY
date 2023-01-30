package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.InformFragmentBinding

class FragmentInform : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //프레그먼트가 처음 실행될 때 실행하는 메소드
        //res폴더에 만들어준 xml파일과 연결해주어야 함.
        val binding = InformFragmentBinding.inflate(layoutInflater)

        //디폴트로 남자 버튼이 활성화되도록.
        binding.btnman.isSelected = true


        //만약 여자 버튼이 클릭된다면?
        binding.btnwoman.setOnClickListener() {
            Log.d("sex", "여자 클릭")
            binding.btnwoman.isSelected = true
            binding.btnman.isSelected = false
            Log.d("sex", "남자 : ${binding.btnman.isSelected}, 여자 : ${binding.btnwoman.isSelected}")
        }

        //만약 남자 버튼이 클릭된다면?
        binding.btnman.setOnClickListener() {
            Log.d("sex", "남자 클릭")
            binding.btnwoman.isSelected = false
            binding.btnman.isSelected = true
            Log.d("sex", "남자 : ${binding.btnman.isSelected}, 여자 : ${binding.btnwoman.isSelected}")

        }

        return binding.root
    }
}