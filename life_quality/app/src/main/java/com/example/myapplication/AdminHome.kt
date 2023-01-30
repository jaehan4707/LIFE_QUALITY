package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceControlViewHost
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityAdminHomeBinding
import com.example.myapplication.MainActivity.Companion.surveyList

class AdminHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAdminHomeBinding.inflate(layoutInflater)
        var check : Boolean = binding.choice.isSelected
        Log.d("check", "초기상태 : ${check}")
            setContentView(binding.root)
        //시작했을때 쫘악 보여주기.
        val Homeadapter = AdminHomeAdapter(surveyList,binding)
        var recyclerView : RecyclerView = binding.questionRecycle
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=Homeadapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        binding.adminHome.setOnClickListener{
            //초기화면으로 돌아가야함.
            Toast.makeText(this,"홈버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java) //홈화면 누르면 mainactivity로 이동.
            startActivity(intent)

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
        binding.choice.setOnClickListener{
            Toast.makeText(this,"선택버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            binding.choice.isSelected = !binding.choice.isSelected

            if(binding.choice.isSelected){
                //binding.choice.setBackgroundColor()
                binding.trash.visibility= View.VISIBLE
                binding.plus.visibility=View.GONE
                val adapter2 = ChoiceAdapter(surveyList,binding)
                recyclerView.layoutManager=LinearLayoutManager(this)
                recyclerView.adapter=adapter2
                recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
            }
            else{ //선택버튼 비활성화
                for(i in 0 .. surveyList.size-1){
                    surveyList[i].selected=false
                }
                binding.trash.visibility=View.GONE
                binding.plus.visibility=View.VISIBLE
                val adapter2 = ChoiceAdapter(surveyList,binding)
                recyclerView.layoutManager=LinearLayoutManager(this)
                recyclerView.adapter=adapter2
                recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
                //binding.choice.setBackgroundColor(Color.parseColor("#60EE913B"))
            }
        }
        /*
        binding.trash.setOnClickListener{//휴지통 버튼을 눌렀을때 선택되는 걸 다 지워준다.
            Toast.makeText(this,"삭제버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            binding.trash.visibility=View.GONE
            binding.plus.visibility=View.VISIBLE
        }
         */
    }
}