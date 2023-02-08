package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.example.myapplication.databinding.ActivityQuestionSelectBinding
import java.sql.Types.NULL

class QuestionSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding  = ActivityQuestionSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
         binding.selectStart.setOnClickListener{ //설문 시작하기 버튼 눌렀을 때
            Toast.makeText(this@QuestionSelect,"설문시작하기 버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
            var checkRadio :Int = binding.group.checkedRadioButtonId
             if(checkRadio==-1){
                 //만약 라디오버튼을 선택하지 않은 채 시작하기 버튼을 눌렀다면 진행하면 안됨.
                 Toast.makeText(this,"설문목록을 선택해주세요",Toast.LENGTH_SHORT).show()
             }
            else{
                var btn = binding.group.findViewById<RadioButton>(checkRadio)
                 //선택된 버튼 잘 뽑아옴. 버튼 text에 해당하는 내용에 db를 불러와야함. db 불러오고 questionMain으로 넘어가야함.
             }
            //해당 버튼의 해당하는 설문리스트를 불러와야함.
        }
        binding.selectClear.setOnClickListener{ //설문 완료 눌렀을떄
            Toast.makeText(this@QuestionSelect,"설문완료하기 버튼을 눌렀습니다",Toast.LENGTH_SHORT).show()
        }
    }
}