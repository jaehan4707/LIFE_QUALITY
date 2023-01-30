package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    companion object {
        var nameList = mutableListOf<String>("EQ5D", "EQVAS", "Fall", "Frailty", "IPAQ", "MNA", "MouthHealth", "SGDSK", "SleepHabit")
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


        val db = Firebase.firestore
        for(i in 0..nameList.size-1) {
            db.collection("${nameList[i]}")
                .get()
                .addOnSuccessListener{ result->
                    for(document in result) {
                        Log.d("What is name", "${nameList[i]}")
                        surveyList.add(TotalSurvey(nameList[i], document.id,
                            document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                            document.data["answer"] as MutableMap<String, String>,false)
                        )
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w("Get Data Error", exception)
                }
        }
        Log.d("설문문항 텍스트 값", "${surveyList.size}")
//        binding.numberSurvey.text = surveyList.size.toString() + "개"

        setContentView(binding.root)
        binding.qStart.setOnClickListener() {
            val intent = Intent(this, QuestionMainpage::class.java)
            startActivity(intent)
        }
        binding.redCircle.setOnClickListener{
            val intent =Intent(this,AdminHome::class.java)
            startActivity(intent)
        }

    }
}