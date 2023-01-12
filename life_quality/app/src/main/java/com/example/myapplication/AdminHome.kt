package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityAdminHomeBinding

class AdminHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.adminHome.setOnClickListener{
            //초기화면으로 돌아가야함.
            Toast.makeText(this,"홈버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
        binding.plus.setOnClickListener(){
            //질문추가 화면으로 넘어가야함.
            val intent=Intent(this@AdminHome,AdminPlus::class.java)
            Toast.makeText(this@AdminHome,"+버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        binding.search.setOnClickListener() {
            //검색버튼을 누르면 해당 질문을 불러와야함.
            Toast.makeText(this,"검색버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
        binding.choice.setOnClickListener {
            //선택버튼을 누르면 화면을 전환해서 선택된 질문을 삭제해야함.
            //누르기 전에는 색깔을 회색으로 주든 희미하게 줘야함.
            Toast.makeText(this,"선택버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
        /* 리사이클러 뷰 규성
        binding.questionRecycle.layoutManager=LinearLayoutManager(this)
        //binding.questionRecycle.adapter=Myapater(dataset)
        binding.questionRecycle.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
         */


    }
}