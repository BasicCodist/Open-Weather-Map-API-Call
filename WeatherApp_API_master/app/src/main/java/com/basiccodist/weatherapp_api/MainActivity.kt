package com.basiccodist.weatherapp_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doAsync {
            RunRequest()
            uiThread { toast("Request Performed") }
        }
    }

    private fun RunRequest()
    {
        val cityName:String = "Delhi"
        //Add your preferred location and API key obtained from OpenWeatherMap.org
        val url = "https://api.openweathermap.org/data/2.5/weather?q=delhi,in&units=metric&appid=0d1c49d9637dc9599e4455ae626ee3f7"
        val resultJson = URL(url).readText()
        Log.d("Weather Report",resultJson)
        val jsonObj = JSONObject(resultJson)
        val main = jsonObj.getJSONObject("main")
        val temp = main.getString("temp")+"°C"
        val minmaxTemp = main.getString("temp_min")+"°C/"+main.getString("temp_max")+"°C"
        val cityNameText = findViewById<TextView>(R.id.textView2) as TextView
        val tempText = findViewById<TextView>(R.id.textView) as TextView
        val minmaxTempText = findViewById<TextView>(R.id.textView3) as TextView
        cityNameText.text = cityName
        tempText.text = temp
        minmaxTempText.text = minmaxTemp
    }
}