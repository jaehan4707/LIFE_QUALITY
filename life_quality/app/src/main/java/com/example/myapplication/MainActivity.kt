package com.example.myapplication

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.myapplication.admin.AdminHome
import com.example.myapplication.question.QuestionMainpage.Companion.curCount
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogStartBinding
import com.example.myapplication.databinding.NotiDialogBinding
import com.example.myapplication.databinding.Smoke1DialogBinding
import com.example.myapplication.question.QuestionSelect
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var waitTime : Long = 0
    companion object {
        var nameList = mutableListOf<String>("EQ5D", "EQVAS", "Fall", "Frailty", "IPAQ", "MNA", "MouthHealth", "SGDSK", "SleepHabit","Yosil","Nutrition","NutritionHazard","SocialNetwork","Drink","Smoke")
        var Total = mutableListOf<TotalSurvey>()
        var type : String = ""
        var answer = mutableListOf<Int>()
        var drinknum : Int = 0
        var smokenum : Int =0
        var Socialnum : Int =0
        var Fallum : Int =2
        var dbid =0
        var surveyList = mutableListOf<TotalSurvey>()
        var address : String = " "
        var relation : String = " "
        val check_list = Array<Boolean>(11) { false }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //질문 개수와 질문 리스트 초기화하고 다시 받아와야함.
        curCount = 0
        Total = mutableListOf<TotalSurvey>()
        
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //해당 앱에서의 권한은 알림권한밖에 없음.
            // 이미 권한이 허용된 경우 처리할 로직
            Log.d("problem", "알림권한이 있습니다")
        } else { //권한이 없을 경우 권한을 요청함.
            TedPermission.create()
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        //Toast.makeText(this@MainActivity, "권한 요청", Toast.LENGTH_SHORT).show()
                        Log.d("problem", "권한요청")
                    }

                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                        //Toast.makeText(this@MainActivity, "권한 거부", Toast.LENGTH_SHORT).show()
                        Log.d("problem", "권한거부")
                    }
                })
                .setDeniedMessage("알림 권한을 거절하신다면\n알림 기능을 사용할 수 없습니다")
                .setPermissions(
                    Manifest.permission.POST_NOTIFICATIONS,
                    Manifest.permission.SCHEDULE_EXACT_ALARM,
                    Manifest.permission.USE_EXACT_ALARM,
                    Manifest.permission.RECEIVE_BOOT_COMPLETED
                )
                .check()
        }

        val db = Firebase.firestore
        for (i in 0..nameList.size - 1) {
            db.collection("${nameList[i]}")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        //Log.d("What is name", "${nameList[i]}")
                        Total.add(
                            TotalSurvey(
                                nameList[i],
                                document.id,
                                document.data["number"] as String,
                                document.data["title"] as String,
                                document.data["type"] as String,
                                document.data["answer"] as MutableMap<String, String>,
                                false
                            )
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("Get Data Error", exception)
                }
        }
        setContentView(binding.root)
        binding.qStart.setOnClickListener() {
            showDialog()
        }
        binding.redCircle.setOnClickListener {
            val intent = Intent(this, AdminHome::class.java)
            startActivity(intent)
        }
        binding.edu.setOnClickListener() {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }
        binding.helpNoti.setOnClickListener {
            Log.d("test", "test testtest")
            val dialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.noti_dialog, null)
            val dialogBinding = NotiDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialogBinding.btnClose.setOnClickListener {
                dialog.dismiss() // 다이얼로그를 닫기
            }
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            dialog.show()
            dialog.window?.setLayout(1000, 1400)
        }
    }

    private fun requestPermission(logic : () -> Unit){
        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    logic()
                }
                override fun onPermissionDenied(deniedPermissions: List<String>) {
                    Toast.makeText(this@MainActivity,
                        "권한을 허가해주세요.",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })
            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_CALENDAR )
            .check()
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