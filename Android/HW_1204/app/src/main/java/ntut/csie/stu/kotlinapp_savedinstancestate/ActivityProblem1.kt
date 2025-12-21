package ntut.csie.stu.kotlinapp_savedinstancestate

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ntut.csie.stu.kotlinapp_savedinstancestate.databinding.ActivityMainBinding

class ActivityProblem1 : AppCompatActivity() {
    lateinit var answer: String;
    var count: Int = 0

    var guessCount = 0;
    fun randomNumber(): String {
        var rand = (0..9999).random().toString();

        while (rand.length != 4) {
            rand = "0$rand";
        }

        return rand;
    }

    fun calc1A2B(answer: String , guess: String): Pair<Int, Int> {
        val maxIndex = minOf(answer.length, guess.length) - 1;

        var a=0;
        var b=0;

        for (i in 0..maxIndex) {
            if (answer[i] == guess[i]) {
                a++;
                continue;
            }

            if (guess[i] in answer) {
                b++;
                continue;
            }
        }

        return Pair(a, b);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_problem2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_title)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Restore state if exists
        if (savedInstanceState != null) {
            val tv = findViewById<TextView>(R.id.guess_history_output);
            // 如果 savedInstanceState 不為 null，表示 Activity 非第一次重建。
            count = savedInstanceState.getInt("Guess Count", 0); // 第二個參數是預設值 (如果找不到 Data)
            tv.text = savedInstanceState.getString("Guess Message", ""); // 第二個參數是預設值 (如果找不到 Data)
        }

        answer = randomNumber();
        addButtonListener();
    }

    fun addButtonListener() {
        val tv = findViewById<TextView>(R.id.guess_history_output);

        findViewById<Button>(R.id.guess_button).setOnClickListener {
            val guess = findViewById<EditText>(R.id.guess_input).text.toString();
            val cmpResult : Pair<Int, Int> = calc1A2B(answer, guess);

            guessCount++;

            tv.text = tv.text.toString() + "($guessCount) 猜測: $guess 結果: ${cmpResult.first}A${cmpResult.second}B\n"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("Guess Count", count)
        outState.putString("Guess Message", findViewById<TextView>(R.id.guess_history_output).text.toString())
    }
}