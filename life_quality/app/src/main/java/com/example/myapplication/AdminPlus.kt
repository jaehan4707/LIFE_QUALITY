package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityAdminPlusBinding



class Answer {
    var content : String=""
    var score : String =""
    constructor()
    constructor(content : String, score : String) {
        this.content = content
        this.score = score
    }
}

data class survey(

    var title: String,
    var type : String,
    var number : String,
    var ans_list : MutableList<Answer>
)

/*
data class survey(
    var sequence : String,
    var content : String
)
*/

class AdminPlus : AppCompatActivity() {
    var check : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAdminPlusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data= mutableListOf<Answer>()
        val result= mutableListOf<survey>()
        var num : Int =0
        var head : String=""
        var type : Int=0
        binding.questionTitle.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                head = binding.questionTitle.text.toString()
            }
        })
        //질문제목은 잘 들어감.
        binding.choice.setOnClickListener {
            binding.choiceLayout.visibility= View.VISIBLE
            binding.inputLayout.visibility=View.GONE
            type=0
        }
        binding.input.setOnClickListener {
            binding.choiceLayout.visibility= View.GONE
            binding.inputLayout.visibility=View.VISIBLE
            type=1
        }

        binding.numInput.setOnClickListener {

            Toast.makeText(this,"입력하기버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            num =binding.responseNum.text.toString().toInt()
            Log.d("num","${num}") //이 숫자를 이용해서 리사이클러뷰 아이템을 조절해야함.
            //num개수만큼 data 더미 데이터 넘겨줘볼까!

            for( i in 1..num){
                data.add(Answer("",""))
            }
            val adapter = AdminPlusAdapter(data, binding)
            val recyclerView : RecyclerView = binding.adminPlus
            recyclerView.layoutManager=LinearLayoutManager(this)
            recyclerView.adapter=adapter
            recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))
        }
        binding.time.setOnClickListener {
            Toast.makeText(this, "시간버튼을 눌렀습니다", Toast.LENGTH_SHORT).show()
            //
        }
        binding.score.setOnClickListener {
            Toast.makeText(this,"점수버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            //
        }
        binding.save.setOnClickListener {
            //저장하기 버튼을 눌렀을때
            result.add(survey(head,type.toString(),num.toString(),data))
            Toast.makeText(this,"저장하기버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
    }
}