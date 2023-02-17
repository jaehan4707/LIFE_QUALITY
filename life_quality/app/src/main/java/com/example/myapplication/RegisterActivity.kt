package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginLayoutBinding
import com.example.myapplication.databinding.RegisterLayoutBinding
import com.example.retrofitpractice.Model.Data
import com.example.retrofitpractice.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : RegisterLayoutBinding
    //데이터 담을 객체 선언
    lateinit var userProfile : Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegisterLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sexSelected = 1 //남자면 1, 여자면 -1로 값이 바뀐다.
        binding.registerMan.setOnClickListener() {
            binding.registerMan.setBackgroundColor(getColor(R.color.main_orange))
            binding.registerWoman.setBackgroundColor(getColor(R.color.gray))
            sexSelected = 1
        }
        binding.registerWoman.setOnClickListener() {
            binding.registerMan.setBackgroundColor(getColor(R.color.gray))
            binding.registerWoman.setBackgroundColor(getColor(R.color.main_orange))
            sexSelected = -1
        }



        binding.finishRegister.setOnClickListener() {

            var id = null
            var phone = binding.registerPhone.text.toString()
            var name = binding.registerName.text.toString()
            var pw = binding.registerPassword.text.toString()
            var year = binding.registerYear.text.toString()
            var month = binding.registerMonth.text.toString()
            var day = binding.registerDay.text.toString()
            var sex = ""
            if(sexSelected == 1) {
                sex = "M";
            }
            else {
                sex = "W";
            }
            var birth = year + month + day; //19990101 형식으로 포맷 맞추기
            userProfile = Data(name, phone, pw, birth, sex)
            //db에 저장하는 코드 만들기
            apiRegister()

            var intent = Intent(this, FinishRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


    fun apiRegister() {

        //1. retrofit 객체 생성
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //2. service 객체 생성

        val apiservice : ApiService = retrofit.create(ApiService::class.java)

        //3. Call 객체 생성
        var registerCall = apiservice.insertUserProfile(userProfile)

        //4. 네트워크 통신
        registerCall.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Log.d("ApiRequest", "${response.body()}")
//                binding.resultText.append("id : ${tickerInfo?.data?.id}\n")
//                binding.resultText.append("name : ${tickerInfo?.data?.name}\n")
//                binding.resultText.append("phone : ${tickerInfo?.data?.phone}\n")
//                binding.resultText.append("address : ${tickerInfo?.data?.address}\n")
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                //호출에 실패했을 때
                Log.d("ApiRequest", "호출 실패 씨발!!")
                call.cancel()
            }

        })


    }
}