package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityAdminPlusBinding
import com.example.myapplication.MainActivity.Companion.surveyList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
    var number : String="",
    var title : String="",
    var type : String="",
    var answer : MutableMap<String,String> = mutableMapOf()
)


class AdminPlus : AppCompatActivity() {
    var check : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAdminPlusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data= mutableListOf<Answer>()
        var num : Int =0
        var head : String=""
        var type : String=""
        var head_type : String=""
        binding.questionType.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                head_type = binding.questionType.text.toString()
            }
        })
        binding.questionTitle.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                head = binding.questionTitle.text.toString()
            }
        })
        binding.adminHome.setOnClickListener{
            Toast.makeText(this,"홈버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.choice.setOnClickListener {
            binding.choiceLayout.visibility= View.VISIBLE
            binding.inputLayout.visibility=View.GONE
            type="0"
        }
        binding.input.setOnClickListener {
            binding.choiceLayout.visibility= View.GONE
            binding.inputLayout.visibility=View.VISIBLE
            type="1"
        }
        binding.numInput.setOnClickListener {
            data.clear()
            Toast.makeText(this,"입력하기버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            num =binding.responseNum.text.toString().toInt()
            Log.d("num","${num}") //이 숫자를 이용해서 리사이클러뷰 아이템을 조절해야함.
            for( i in 0..num-1){
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
        }
        binding.score.setOnClickListener {
            Toast.makeText(this,"점수버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
        binding.save.setOnClickListener {
            //저장하기 버튼을 눌렀을때
            var m = mutableMapOf<String,String>()
            for(i in 0 .. data.size-1) {
                m.put(data[i].score, data[i].content)
            }
            surveyList.add(
                TotalSurvey(head_type,surveyList.size.toString(
            ),num.toString(),head,type,m,false)
            )
            var Result = survey(num.toString(),head,type,m)
            Log.d("test","${Result.title.toString()},${Result.number.toString()}")
            var n =0
            for( i in 0 .. surveyList.size-1){
                if(surveyList[i].surveyType==head_type){
                    n++
                }
            }
            val Db = Firebase.firestore
            Db.collection(head_type).document(n.toString())
                .set(Result)
                .addOnSuccessListener {Log.d("test","성공")}
                .addOnFailureListener { Log.d("test","실패")}
            Toast.makeText(this,"저장하기버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AdminHome::class.java) //홈화면 누르면 mainactivity로 이동.
            startActivity(intent)
        }
    }
}