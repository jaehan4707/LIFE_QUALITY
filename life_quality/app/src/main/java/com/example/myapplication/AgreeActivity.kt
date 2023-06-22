package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LoginActivity.Companion.Db
import com.example.myapplication.LoginActivity.Companion.phone
import com.example.myapplication.SplashActivity.Companion.Total
import com.example.myapplication.SplashActivity.Companion.token
import com.example.myapplication.SplashActivity.Companion.user
import com.example.myapplication.databinding.ActivityAgreeBinding
import com.example.myapplication.databinding.AgreeDialogBinding
import com.example.myapplication.model.User
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgreeActivity : AppCompatActivity() { //개인정보 동의하는 액티비티
    val binding: ActivityAgreeBinding by lazy {
        ActivityAgreeBinding.inflate(layoutInflater)
    }
    companion object {
        var Sex: String = ""
        var Age: String = ""
        var Family: String = ""
        var Study: String = ""
        var Helath: String = ""
        var Smoke: String = ""
        var Drink: String = ""

    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java) //intent
        val database = FirebaseDatabase.getInstance()
        setContentView(binding.root)
        Log.d("problem", "FCM token is ${token}")
        val userCollectionRef = Db.collection("User")
        Log.d("problem","tototot : ${Total.size}")
        binding.yes.setOnClickListener {
            val dialogBinding = AgreeDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this)
            val dialogLayoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window?.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
            dialog.window?.attributes = dialogLayoutParams
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels
            dialog.window?.setLayout(screenWidth, screenHeight)
            dialog.show()
            clickRadio(dialogBinding.rgSex, "Sex")
            clickRadio(dialogBinding.rgFamily, "Family")
            clickRadio(dialogBinding.rgStudy, "Scholarship")
            clickRadio(dialogBinding.rgHealth, "Medical_insurance")
            clickRadio(dialogBinding.rgDrink, "Drink")
            clickRadio(dialogBinding.rgSmoke, "Smoke")

            closeDialog(dialogBinding)
            dialogBinding.agreeClear.setOnClickListener { //완료하기 버튼.
                phone = dialogBinding.editPhone.text.toString()
                Age = dialogBinding.editAge.text.toString()
                Log.d("problem","${phone},${Age}")
                val isValidPhoneNumber = android.util.Patterns.PHONE.matcher(phone).matches()
                if (!isValidPhoneNumber || Sex.isEmpty() || Age.isEmpty() || Family.isEmpty()||Study.isEmpty()
                    ||Helath.isEmpty()||Smoke.isEmpty()||Drink.isEmpty()) { // 입력된게 전화번호 형식이면
                        Toast.makeText(this, "빈칸이 있어요!! 선택을 완벽하게 해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        user=User(Sex,Age,Family,Study,Helath,Smoke,Drink,phone)
                        val newUserDoc = userCollectionRef.document(phone)
                        newUserDoc.set(user)
                        .addOnSuccessListener {
                            Log.d("problem","유저정보 저장 성공")
                        }
                        .addOnFailureListener {
                            Log.d("problem","저장 실패")
                        }
                    /*
                    val informationCollection = newUserDoc.collection("Information")
                    informationCollection.document("개인정보").set(user)
                        .addOnSuccessListener {
                            // 문서 추가 성공
                            Toast.makeText(this, "사용자 정보를 저장했습니다.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            // 문서 추가 실패
                            Log.e("Error", "사용자 정보 저장 중 오류 발생: $e")
                        }
                     */
                    /*
                    newUserDoc.set(user)
                            .addOnSuccessListener {
                                Log.d("problem","유저정보 저장 성공")
                            }
                            .addOnFailureListener {
                                Log.d("problem","저장 실패")
                            }
                     */
                    val infoRef = database.getReference("User/phone/${phone}/information/") //toekn 경로에 저장한다.
                    infoRef.setValue(user).addOnSuccessListener {
                        Log.d("problem", "info 저장 성공")
                        }
                        .addOnFailureListener { exception ->
                            Log.d("problem", "answer 저장 실패", exception)
                        }

                        Log.d("problem", "${user}")
                        dialog.dismiss() // 다이얼로그를 닫기
                        startActivity(intent)
                    }
                }
            }
            binding.no.setOnClickListener {
                Toast.makeText(this, "참여를 원치 않으신다면 해당 어플리케이션을 사용하실 수 없습니다", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    private fun closeDialog(dialogBinding: AgreeDialogBinding){
        dialogBinding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // 터치 이벤트가 발생하면 키보드를 숨깁니다.
                Log.d("problem","터치이벤트가 발생해서 키보드르 숨깁니다.")
                val inputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(dialogBinding.root.windowToken, 0)
                dialogBinding.root.clearFocus()
            }
            false
        }
    }

    private fun clickRadio(radioGroup: RadioGroup, str: String) {
        radioGroup.setOnCheckedChangeListener { _, checkedId -> // 라디오 버튼의 선택 상태가 변경되었을 때 호출되는 메서드입니다.
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            val selectedText = radioButton.text.toString()
            when(str){
                "Sex"-> Sex=selectedText
                "Age"->Age=selectedText
                "Family"->Family=selectedText
                "Scholarship"->Study=selectedText
                "Medical_insurance" ->Helath=selectedText
                "Smoke"->Smoke=selectedText
                "Drink"->Drink=selectedText
            }
        }
    }
}