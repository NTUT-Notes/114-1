package ntut.csie.s54007.setcontentview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    int idxColor = 0;

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

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSecond();
            }
        });
    }

    // 重新來過的概念: 想像是重新進入程式，所以需要依據當下的 lauyout 來連結各個 view 元件。
    private void switchToSecond() {
        // switch to second ContentView first,
        this.setContentView(R.layout.activity_third);
        // connect to the components needed,
        Button btnBack = (Button) findViewById(R.id.button8);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToMain();
            }
        });
    }

    // 重新來過的概念:
    private void switchToMain() {
        // switch to second ContentView first,
        this.setContentView(R.layout.activity_main);
        // connect to the components needed,
        TextView tv = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSecond();
            }
        });
    }

}