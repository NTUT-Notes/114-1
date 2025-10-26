package com.yfhd.a2025_10_09;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Q2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_q2);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    @SuppressLint("DefaultLocale")
    public void onCalcBMI(View view) {
        // This method will be called when the button is clicked
        // Add your desired functionality here, e.g., showing a Toast
        EditText heightTextview = findViewById(R.id.textInputEditText);
        EditText weightTextview = findViewById(R.id.textInputEditText2);

        if (heightTextview.getText().toString().isEmpty()) {
            Toast.makeText(this, "請輸入身高", Toast.LENGTH_SHORT).show();
            return;
        }

        if (weightTextview.getText().toString().isEmpty()) {
            Toast.makeText(this, "請輸入體重", Toast.LENGTH_SHORT).show();
            return;
        }

        double height = Double.parseDouble(heightTextview.getText().toString());
        double weight = Double.parseDouble(weightTextview.getText().toString());

        if (Double.isNaN(height) || Double.isNaN(weight)) {
            Toast.makeText(this, "無法解析身高或體重數值", Toast.LENGTH_SHORT).show();
            return;
        }

        double bmi = weight / (height * height / 10000);

        TextView resultTextview = findViewById(R.id.textView6);
        String historyMessage = resultTextview.getText().toString();
        historyMessage += String.format("\n 身高：%.1fcm, 體重：%.1fkg, BMI: %.1f, %s", height, weight, bmi, bmiToRate(bmi));

        resultTextview.setText(historyMessage);
    }

    String bmiToRate(double bmi) {
        if (bmi > 25) {
            return "過重";
        }

        if (bmi < 18) {
            return "過輕";
        }

        return "正常";
    }
}