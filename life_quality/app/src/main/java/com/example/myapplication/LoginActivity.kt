package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginLayoutBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    lateinit var binding : LoginLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //로그인 버튼 클릭시
        binding.login.setOnClickListener() {
            var phone = binding.login.text.toString()

            if(phone.length != 11) { //핸드폰번호가 올바르게 입력되지 않았다면?
                Snackbar.make(binding.loginLayout, "핸드폰 번호 또는 비밀번호가 올바르지 않습니다.", Snackbar.LENGTH_SHORT).show()
            }
            else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        //회원가입 버튼 클릭시
        binding.goRegister.setOnClickListener() {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}