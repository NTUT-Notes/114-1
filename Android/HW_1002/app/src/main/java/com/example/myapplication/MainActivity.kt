package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("SetTextI18n")
    fun onBtnClick(view: View) {
        val myTextView: TextView = findViewById(R.id.title_string)

        // Change the text of the TextView
        myTextView.text = "資工二 113590021 邱冠勛"
        myTextView.setTextColor(Color.BLUE)

        // Set font size in scaled pixels (sp)
        myTextView.textSize = 24f

        Toast.makeText(this, "歡迎使用本程式", Toast.LENGTH_SHORT).show();
    }


}