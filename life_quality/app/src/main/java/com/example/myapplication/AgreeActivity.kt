package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.myapplication.admin.AdminHome
import com.example.myapplication.databinding.ActivityAgreeBinding
import com.example.myapplication.databinding.AgreeDialogBinding
import com.example.myapplication.databinding.NotiDialogBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AgreeActivity : AppCompatActivity() { //개인정보 동의하는 액티비티
    val binding : ActivityAgreeBinding by lazy{
        ActivityAgreeBinding.inflate(layoutInflater)
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java) //intent
        val database = FirebaseDatabase.getInstance()
        val userRef =
            database.getReference("User/token/${SplashActivity.token!!}") //toekn 경로에 저장한다.
        Log.d("problem", "FCM token is ${SplashActivity.token}")
        //여기서 토큰이 없다면 바로 넘어가야함.
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {  // 해당 token 값이 이미 존재하므로 작업을 멈춥니다.
                    Log.d("problem", "해당 token 값이 이미 존재합니다. 작업을 멈추고, mainActivity로 이동합니다")
                    //startActivity(intent)
                    setContentView(binding.root)
                    return
                }
                setContentView(binding.root)
                // 해당 token 값이 존재하지 않으므로 작업을 계속 진행합니다.
                userRef.setValue(SplashActivity.token)
                    .addOnSuccessListener {
                        Log.d("problem", "token 저장 성공")
                        // 작업을 진행할 코드를 추가하세요
                    }
                    .addOnFailureListener {
                        Log.d("problem", "token 저장 실패")
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // 읽기 작업이 취소된 경우에 호출됩니다.
                Log.d("problem", "데이터베이스 읽기 작업이 취소되었습니다.", databaseError.toException())
            }
        })
        binding.yes.setOnClickListener {
            val dialogView = LayoutInflater.from(this@AgreeActivity).inflate(R.layout.agree_dialog, null)
            val dialogBinding = AgreeDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this)
            val dialogLayoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window?.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
            dialog.window?.attributes = dialogLayoutParams
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val RgSex = dialogBinding.rgSex
            val RgAge = dialogBinding.rgAge
            val RgDrink = dialogBinding.rgDrink
            val RgFamily = dialogBinding.rgFamily
            val RgSmoke = dialogBinding.rgSmoke
            val RgHealth = dialogBinding.rgHealth
            val RgStudy = dialogBinding.rgStudy
            var selectSex = RgSex.checkedRadioButtonId
            var selectAge = RgAge.checkedRadioButtonId
            var selectDrink = RgDrink.checkedRadioButtonId
            var selectFamily = RgFamily.checkedRadioButtonId
            var selectSmoke = RgSmoke.checkedRadioButtonId
            var selectHealth = RgHealth.checkedRadioButtonId
            var selectStudy = RgStudy.checkedRadioButtonId

            dialogBinding.agreeClose.setOnClickListener {
                val phoneNumber = dialogBinding.editPhone.text.toString()
                Log.d("problem","전화번호 : ${dialogBinding.editPhone.text.toString()}")
                val isValidPhoneNumber = android.util.Patterns.PHONE.matcher(phoneNumber).matches()
                if (isValidPhoneNumber) { // 입력된게 전화번호 형식이면
                    Log.d("problem","적절한 전화번호 양식")
                    dialog.dismiss() // 다이얼로그를 닫기
                } else {
                    Log.d("problem","잘못ㅣ 전화번호 양식")
                    Toast.makeText(this,"전화번호부 형식이 잘못된거같아요!!\n다시 입력해주세요",Toast.LENGTH_SHORT).show()
                }
            }
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels
            dialog.window?.setLayout(screenWidth, screenHeight)

            dialog.show()
        }
        binding.no.setOnClickListener {
            Toast.makeText(this,"참여를 원치 않으신다면 해당 어플리케이션을 사용하실 수 없습니다",Toast.LENGTH_SHORT).show()
        }
    }
}