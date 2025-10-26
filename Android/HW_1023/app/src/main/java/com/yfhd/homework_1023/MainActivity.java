package com.yfhd.homework_1023;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addListenerOnButton();
        addListenerOnRadioButton();
    }

    public void addListenerOnRadioButton() {
        RadioGroup drinkGroup = findViewById(R.id.drink_type);
        drinkGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This method is called when the checked RadioButton changes within the RadioGroup.
                // 'checkedId' is the resource ID of the newly selected RadioButton.

                int childCount = group.getChildCount();
                for (int i=0; i<childCount; i++) {
                    RadioButton button = (RadioButton) group.getChildAt(i);
                    button.setTextColor(0xFF000000);
                }

                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    selectedRadioButton.setTextColor(0xFFFF0000);
                    // Perform actions based on the selected RadioButton
                    // e.g., display a Toast, update a TextView, etc.
                    // Toast.makeText(YourActivity.this, "Selected: " + selectedText, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addListenerOnButton() {
        RadioGroup drinkGroup = findViewById(R.id.drink_type);
        RadioGroup tempGroup = findViewById(R.id.temp_type);
        TextView output = findViewById(R.id.output_view);
        TextView amountView = findViewById(R.id.amount);

        Button btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                int drinkId, tempId, amount;
                // get selected radio button from radioGroup
                try {
                    drinkId = drinkGroup.getCheckedRadioButtonId();
                    tempId = tempGroup.getCheckedRadioButtonId();
                } catch (Exception e) {
                    showMyToast("無效的飲料選擇");
                    return;
                }

                try {
                    amount = Integer.parseInt(amountView.getText().toString());
                } catch (Exception e) {
                    showMyToast("無效的杯數");
                    return;
                }

                String drink = ((RadioButton) findViewById(drinkId)).getText().toString();
                String temp = ((RadioButton) findViewById(tempId)).getText().toString();

                output.setText(String.format("%s\n%s %s %d", output.getText(), drink, temp, amount));
            }

        });
    }

    public void showMyToast(String message) {
        Context context = getApplicationContext(); // Or 'this' if inside an Activity
        int duration = Toast.LENGTH_SHORT; // Or Toast.LENGTH_LONG for a longer duration

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}