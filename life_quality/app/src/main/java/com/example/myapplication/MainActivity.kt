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
        setContentView(binding.root)

        val db = Firebase.firestore
        for(i in 0..nameList.size-1) {
            Log.d("name", "${nameList[i]}")
            db.collection("${nameList[i]}")
                .get()
                .addOnSuccessListener{ result->
                    for(document in result) {
                        Log.d("result Size", "${result.size()}")
                        when(nameList[i]) {
                            "EQ5D" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                eq5dList.add(EQ5D(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "EQVAS" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                eqvasList.add(EQVAS(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "Fall" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                fallList.add(Fall(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "Frailty" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                frailtyList.add(Frailty(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "IPAQ" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                ipaqList.add(IPAQ(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "MNA" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                mnaList.add(MNA(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "MouthHealth" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                mouthhealthList.add(
                                    MouthHealth(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                )
                                )
                            }
                            "SGDSK" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                sgdskList.add(SGDSK(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                ))
                            }
                            "SleepHabit" -> {
                                Log.d("Success!!!", "${nameList[i]}")
                                sleephabitList.add(
                                    SleepHabit(document.id,
                                    document.data["number"] as String,document.data["title"] as String, document.data["type"] as String,
                                    document.data["answer"] as MutableMap<String, String>
                                )
                                )
                            }
                        }
                    }
                }
                .addOnFailureListener{ exception ->
                    Log.w("Get Data Error", exception)

                }
        }

        binding.qStart.setOnClickListener() {
            Log.d("Finish!!!", "${sgdskList.get(0).answer}")
            Log.d("Success!!!", "${sgdskList.get(1).answer}")
            val intent = Intent(this, QuestionMainpage::class.java)
            startActivity(intent)
        }
        binding.redCircle.setOnClickListener{
            val intent =Intent(this,AdminHome::class.java)
            startActivity(intent)
        }

    }
}