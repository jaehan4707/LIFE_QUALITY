package com.example.myapplication.question

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.myapplication.SplashActivity.Companion.address
import com.example.myapplication.SplashActivity.Companion.relation
import com.example.myapplication.question.QuestionMainpage.Companion.group

import com.example.myapplication.question.QuestionMainpage.Companion.tempSurvey
import com.example.myapplication.R
import com.example.myapplication.databinding.Type7FragmentBinding

class Fragment7 : Fragment() {
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
        var binding = Type7FragmentBinding.inflate(layoutInflater) //만들어준 xml파일을 binding한다.
        if(tempSurvey.type.toInt()!=0){
            binding.address.visibility=View.VISIBLE
            binding.relationship.visibility=View.VISIBLE
        }
        else{
            binding.address.visibility=View.GONE
            binding.relationship.visibility=View.GONE
        }
        binding.type7Number.text = "문항 " + QuestionMainpage.curCount.toString()
        //binding.type7Number.text ="문항 " + tempSurvey.id
        binding.type7Title.text = QuestionMainpage.tempSurvey.title.toString()

        for ((key, value) in QuestionMainpage.tempSurvey.answer) {
            keyList.add(key)
            valueList.add(value)
            Log.d("tempSurvey", "${tempSurvey.number}")
            Log.d("tempSurvey", "${tempSurvey.answer}")
        }

        Log.d("test","7번째 프래그먼트")
        binding.rb1.text = valueList.get(0)
        binding.rb2.text = valueList.get(1)
        binding.rb3.text = valueList.get(2)
        binding.rb4.text = valueList.get(3)
        binding.rb5.text = valueList.get(4)
        binding.rb6.text = valueList.get(5)
        binding.rb7.text = valueList.get(6)
        var view = inflater.inflate(R.layout.type7_fragment, container, false)
        group = binding.groupF7
        group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                binding.rb1.id -> QuestionMainpage.Id = (keyList[0].toDouble())
                binding.rb2.id -> QuestionMainpage.Id = (keyList[1].toDouble())
                binding.rb3.id -> QuestionMainpage.Id = (keyList[2].toDouble())
                binding.rb4.id -> QuestionMainpage.Id = (keyList[3].toDouble())
                binding.rb5.id -> QuestionMainpage.Id = (keyList[4].toDouble())
                binding.rb6.id -> QuestionMainpage.Id = (keyList[5].toDouble())
                binding.rb7.id -> QuestionMainpage.Id = (keyList[6].toDouble())
            }
        }

        if(binding.address.visibility==View.VISIBLE && binding.relationship.visibility==View.VISIBLE)
        {
            binding.editAddress.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    address=p0.toString()
                    Log.d("test","주소 1 : $address")
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
            binding.editRelation.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    relation = p0.toString()
                    Log.d("test","관계  1 : $relation")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        }
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(this@Fragment7.context, "뒤로갈 수 없습니다.", Toast.LENGTH_SHORT)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}