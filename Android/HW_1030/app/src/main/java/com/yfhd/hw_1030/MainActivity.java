package com.yfhd.hw_1030;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int totalSum = 0;

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

        addSumListener();
        addConfirmListener();
        addClearButtonListener();
        addRadioButtonRedSelectedListener();
    }

    private void _ToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("DefaultLocale")
    private void _onDrinkConfirm() {
        RadioGroup drinkGroup = (RadioGroup ) findViewById(R.id.drink_type);
        RadioGroup tempGroup = (RadioGroup ) findViewById(R.id.temp_type);
        RadioGroup sugarGroup = (RadioGroup ) findViewById(R.id.sugar_type);

        if ((int) drinkGroup.getCheckedRadioButtonId() < 0) {
            _ToastMessage("請選擇飲料類型");
            return;
        }

        if ((int) tempGroup.getCheckedRadioButtonId() < 0) {
            _ToastMessage("請選擇冷熱類型");
            return;
        }

        if ((int) sugarGroup.getCheckedRadioButtonId() < 0) {
            _ToastMessage("請選擇加料類型");
            return;
        }

        RadioButton selectedDrink = (RadioButton) findViewById(drinkGroup.getCheckedRadioButtonId());
        RadioButton selectedTemp = (RadioButton) findViewById(tempGroup.getCheckedRadioButtonId());
        RadioButton selectedSugar = (RadioButton) findViewById(sugarGroup.getCheckedRadioButtonId());

        var result = selectedDrink.getText().toString().split(" ");
        int price = Integer.parseInt(result[1]);
        String drink = result[0];

        TextView input = findViewById(R.id.amount);

        int count;

        try {
            count = Integer.parseInt(input.getText().toString());
        } catch (Exception e) {
            _ToastMessage("請輸入有效的杯數");
            return;
        }

        TextView view = (TextView) findViewById(R.id.buy_log);
        view.append(String.format("%s(%s, %s) x %d => %d\n",
            drink,
            selectedTemp.getText(),
            selectedSugar.getText(),
            count,
            count * price
        ));

        totalSum += count * price;
    }

    @SuppressLint("DefaultLocale")
    private  void _onSumClicked() {
        TextView view = (TextView) findViewById(R.id.buy_log);

        view.append(String.format("合計：%d元\n", totalSum));
    }

    private void _onCheckedChanged(RadioGroup group, int checkedId) {
        int buttonCount = group.getChildCount();

        for (int i=1; i<buttonCount; i++) {
            RadioButton button = (RadioButton) group.getChildAt(i);
            button.setTextColor(Color.rgb(0, 0, 0));
        }

        RadioButton selected = (RadioButton) findViewById(checkedId);
        selected.setTextColor(Color.rgb(255, 0, 0));
    }

    private void _clearBuyLogs() {
        totalSum = 0;
        TextView view = (TextView) findViewById(R.id.buy_log);
        view.setText("");
    }

    public void addSumListener() {
        Button btn = findViewById(R.id.sum_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _onSumClicked();
            }
        });
    }

    public void addConfirmListener() {
        Button btn = (Button) findViewById(R.id.confirm_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _onDrinkConfirm();
            }
        });
    }

    public void addClearButtonListener() {
        Button btn = (Button) findViewById(R.id.clear_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _clearBuyLogs();
            }
        });
    }
    public void addRadioButtonRedSelectedListener() {
        RadioGroup drinkGroup = (RadioGroup ) findViewById(R.id.drink_type);
        RadioGroup tempGroup = (RadioGroup ) findViewById(R.id.temp_type);
        RadioGroup sugarGroup = (RadioGroup ) findViewById(R.id.sugar_type);

        drinkGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                _onCheckedChanged(group, checkedId);
            }
        });

        tempGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                _onCheckedChanged(group, checkedId);
            }
        });

        sugarGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                _onCheckedChanged(group, checkedId);
            }
        });

    }
}