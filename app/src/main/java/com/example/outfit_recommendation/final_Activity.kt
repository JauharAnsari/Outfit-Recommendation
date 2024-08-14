package com.example.outfit_recommendation

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class final_Activity : AppCompatActivity() {
    lateinit var Temprature : TextView
    lateinit var Humidity : TextView
    lateinit var Pressure : TextView
    lateinit var WeatherDiscripition : TextView
    lateinit var WindSpeed : TextView
    lateinit var outfitResult : TextView
    lateinit var message : TextView
    lateinit var TipsOne : TextView
    lateinit var TipsTwo : TextView
    lateinit var TipsThree : TextView
    lateinit var zara : ImageView
    lateinit var amazon : ImageView
    lateinit var flipkart : ImageView
    lateinit var backButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        Temprature = findViewById(R.id.temperature)
        Humidity = findViewById(R.id.humidity)
        Pressure = findViewById(R.id.pressure)
        WeatherDiscripition = findViewById(R.id.weatherDiscription)
        WindSpeed = findViewById(R.id.windSpeed)
        outfitResult = findViewById(R.id.textView7)
        message = findViewById(R.id.textView8)
        TipsOne  = findViewById(R.id.tipsOne)
        TipsTwo = findViewById(R.id.tipsTwo)
        TipsThree = findViewById(R.id.tipsThree)
        zara = findViewById(R.id.imageView4)
        amazon = findViewById(R.id.imageView5)
        flipkart = findViewById(R.id.flipkartIcon)
        backButton = findViewById(R.id.button)

        //dbc629cd73f2337146d43a88a35a1d32

        var Name = intent.getStringExtra("NAME")
        var City = intent.getStringExtra("CITY")

        zara.setOnClickListener {
            openWebsite("https://www.zara.com/in/")
        }
        amazon.setOnClickListener {
            openWebsite("https://www.amazon.in/")
        }
        flipkart.setOnClickListener {
            openWebsite("https://www.flipkart.com/")
        }


        message.text="Hello $Name, here are the Details of the Weather in $City and our Outfit recommendation for you  "
        backButton.setOnClickListener {
            startActivity(Intent(this,second_Activity::class.java))
        }



        val retrofitBuiler = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
            .create(APIinterface::class.java)

        val response = retrofitBuiler.getAllMyData("$City","dbc629cd73f2337146d43a88a35a1d32","metric")

        response.enqueue(object : Callback<myWeatherData?> {
            override fun onResponse(call: Call<myWeatherData?>, response: Response<myWeatherData?>) {

               var responseBody = response.body()
                val cityTemperature = responseBody?.main?.temp.toString()

                if (response.isSuccessful&&response !=null) {


                    Temprature.text = cityTemperature + "°C"

                    val cityHumidity = responseBody?.main?.humidity.toString()
                    Humidity.text = cityHumidity + "%"

                    val cityPressure = responseBody?.main?.pressure.toString()
                    Pressure.text = cityPressure + "mb"

                    val cityWeatherDis = responseBody?.weather?.firstOrNull()?.main;"unknown"
                    WeatherDiscripition.text = cityWeatherDis.toString()

                    val cityWindSpeed = responseBody?.wind?.speed.toString()
                    WindSpeed.text = cityWindSpeed+"m/s"

                }

                    if (cityTemperature>=30 .toString() ){
                        outfitResult.text = "Wear Shorts and T-shirt"
                        TipsOne.text = "⭐Wear breathable , light cloths"
                        TipsTwo.text = "⭐Use Sunscreen to protect your skin"
                        TipsThree.text = "⭐Stay in Shaded areas to avoid overheating"
                    }
                    else if (cityTemperature<20 .toString() && cityTemperature>10 .toString()){
                        outfitResult.text = "Baggy Jeans , Jacket , Hoodies"
                        TipsOne.text ="⭐Wear multiple layers of clothing"
                        TipsTwo.text ="⭐Use Hat , Gloves,Scarf and Proper Footwear"
                        TipsThree.text="⭐Use Moisture "
                    }
                     else if ( cityTemperature<30 .toString() && cityTemperature>20.toString() ){
                        outfitResult.text = "Wear Jeans,Long-sleeved shirts,Light jackets for Cooler Evening"
                         TipsTwo.text="⭐Use Sunscreen with SPF 30 "
                        TipsTwo.text = "⭐Use Sunglass"
                        TipsThree.text= "⭐Drink water at time"

                     }
                else{
                        outfitResult.text = "Winter Wear , Jacket , Winter Coat"
                        TipsOne.text ="⭐Wear multiple layers of clothing"
                        TipsTwo.text ="⭐Use Hat , Gloves,Scarf and Proper Footwear"
                        TipsThree.text="⭐Use Moisture "
                    }



            }

            override fun onFailure(call: Call<myWeatherData?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}