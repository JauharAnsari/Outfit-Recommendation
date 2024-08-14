package com.example.outfit_recommendation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class second_Activity : AppCompatActivity() {
    lateinit var nextButton : Button
    lateinit var name : EditText
    lateinit var city : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        nextButton= findViewById(R.id.nextButton)
        name = findViewById(R.id.nameEditText)
        city = findViewById(R.id.cityEditText)






        nextButton.setOnClickListener {
            var yourName = name.text.toString()
            var yourCity = city.text.toString()

            if (yourName.isEmpty()||yourCity.isEmpty()){
                Toast.makeText(this,"Please Fill your Name and City",Toast.LENGTH_LONG).show()
            }
            else {
                var i = Intent(this, final_Activity::class.java)
                i.putExtra("NAME", yourName)
                i.putExtra("CITY", yourCity)
                startActivity(i)
            }


        }



    }
}