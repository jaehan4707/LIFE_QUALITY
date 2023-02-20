package com.example.myapplication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.QuestionMainpage.Companion.curCount
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogStartBinding
import com.example.myapplication.databinding.ItemBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var waitTime : Long = 0
    companion object {
        var nameList = mutableListOf<String>("EQ5D", "EQVAS", "Fall", "Frailty", "IPAQ", "MNA", "MouthHealth", "SGDSK", "SleepHabit","Yosil","Nutrition","NutritionHazard","SocialNetwork","Drink","Smoke")
        var Total = mutableListOf<TotalSurvey>()
        var type : String = ""
        var answer = mutableListOf<Int>()

        var surveyList = mutableListOf<TotalSurvey>()
        var eq5dList = mutableListOf<EQ5D>()
        var eqvasList = mutableListOf<EQVAS>()
        var fallList = mutableListOf<Fall>()
        var frailtyList = mutableListOf<Frailty>()
        var ipaqList = mutableListOf<IPAQ>()
        var mnaList = mutableListOf<MNA>()
        var mouthhealthList = mutableListOf<MouthHealth>()
        var sgdskList = mutableListOf<SGDSK>()
        var sleephabitList = mutableListOf<SleepHabit>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //질문 개수와 질문 리스트 초기화하고 다시 받아와야함.
        curCount = 0
        Total = mutableListOf<TotalSurvey>()


        val db = Firebase.firestore
        for(i in 0..nameList.size-1) {
            db.collection("${nameList[i]}")
                .get()
                .addOnSuccessListener{ result->
                    for(document in result) {
                        Log.d("What is name", "${nameList[i]}")
                        Total.add(
                            TotalSurvey(
                                nameList[i], document.id,
                            document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                            document.data["answer"] as MutableMap<String, String>,false)
                        )
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w("Get Data Error", exception)
                }
        }

        setContentView(binding.root)
        binding.qStart.setOnClickListener() {
            showDialog()
        }
        binding.redCircle.setOnClickListener{
            val intent =Intent(this, AdminHome::class.java)
            startActivity(intent)
        }
        binding.edu.setOnClickListener() {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }
    }

    //커스텀 다이얼로그 띄우는 부분
    fun showDialog() {
        var dialogBinding = DialogStartBinding.inflate(layoutInflater)
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)


        dialogBinding.dialogStart.setOnClickListener() {
            //여기를 바꿔줬음. -> 다이얼로그 시작하기 누르면 -> 목록을 정할수 있도록 해줄생각.
            var intent = Intent(this, QuestionSelect::class.java)
            startActivity(intent)
            dialog.dismiss()
        }
        dialogBinding.dialogBack.setOnClickListener() {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(1000, 1000)
    }

    override fun onBackPressed() {
        //뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - waitTime >= 2000) {
            waitTime = System.currentTimeMillis()
            Snackbar.make(binding.mainActivity,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Snackbar.LENGTH_LONG).show()
        }
        else {
            finish() //종료
        }
    }
}