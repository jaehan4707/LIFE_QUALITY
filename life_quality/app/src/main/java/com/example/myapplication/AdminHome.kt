package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            Toast.makeText(this,"선택버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            binding.trash.visibility= View.VISIBLE
            binding.plus.visibility=View.GONE
        }
        binding.trash.setOnClickListener{//휴지통 버튼을 눌렀을때 선택되는 걸 다 지워준다.
            Toast.makeText(this,"삭제버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            binding.trash.visibility=View.GONE
            binding.plus.visibility=View.VISIBLE
        }
    }
}