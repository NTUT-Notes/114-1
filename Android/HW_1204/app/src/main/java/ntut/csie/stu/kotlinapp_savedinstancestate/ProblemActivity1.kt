package ntut.csie.stu.kotlinapp_savedinstancestate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ntut.csie.stu.kotlinapp_savedinstancestate.databinding.ActivityProblem1Binding

val daysInMonth = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
class ProblemActivity1 : AppCompatActivity() {
    lateinit var binding: ActivityProblem1Binding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_problem1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityProblem1Binding.inflate(layoutInflater);
        setContentView(binding.root);

        addSpinnerListener();
    }

    @SuppressLint("ResourceType")
    fun addSpinnerListener() {
        val months = Array(12) { it -> it + 1 }

        val monthAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            months
        )
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.monthSpinner.adapter = monthAdapter;

        binding.monthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // 當選擇一個項目時被呼叫
//                val selectedMonth = parent.getItemAtPosition(position).toString();
//                setDayByMonth(selectedMonth.toInt());
                val month = binding.monthSpinner.selectedItem.toString().toInt();
                setDayByMonth(month);
                updateResult();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 當沒有選擇任何項目時被呼叫 (例如：下拉列表關閉，但未做任何選擇)
            }
        }
    }

    fun setDayByMonth(month: Int) {
        if (month-1 >= 12 || month < 1) {
            throw Exception("Month out of range");
        }

        val daysInMonth = daysInMonth[month-1];
        val days = Array(daysInMonth) { it -> it + 1 }

        val dayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            days
        )
        binding.daySpinner.adapter = dayAdapter;
        binding.daySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                updateResult();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 當沒有選擇任何項目時被呼叫 (例如：下拉列表關閉，但未做任何選擇)
            }
        }

    }

    fun updateResult() {
        val month = binding.monthSpinner.selectedItem.toString().toInt();
        val day = binding.daySpinner.selectedItem.toString().toInt();

        val daySinceYearStart = clacDaySinceYearStart(month, day);
        val zodiacSign = getZodiacSignIdiomatic(daySinceYearStart);

        binding.resultTv.text = zodiacSign;
    }

    fun clacDaySinceYearStart(month: Int, day: Int): Int {
        var days = 0;
        for (i in 0..<month-1) {
            days += daysInMonth[i];
        }

        days += day;
        return days;
    }

    fun getZodiacSignIdiomatic(dayOfYear: Int): String {
        return when (dayOfYear) {
            in 1..19   -> "摩羯座"
            in 20..49  -> "水瓶座"  // 1/20 - 2/18
            in 50..79  -> "雙魚座"  // 2/19 - 3/20
            in 80..109 -> "牡羊座"  // 3/21 - 4/19
            in 110..140 -> "金牛座" // 4/20 - 5/20
            in 141..171 -> "雙子座" // 5/21 - 6/20
            in 172..203 -> "巨蟹座" // 6/21 - 7/22
            in 204..234 -> "獅子座" // 7/23 - 8/22
            in 235..265 -> "處女座" // 8/23 - 9/22
            in 266..295 -> "天秤座" // 9/23 - 10/22
            in 296..325 -> "天蠍座" // 10/23 - 11/21
            in 326..355 -> "射手座" // 11/22 - 12/21
            in 356..366 -> "摩羯座" // 12/22 - 12/31
            else -> "無效日期"
        }
    }

}

/*

private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // assign the content of a spinner:
        // meethod 1:
        val items = listOf("Apple", "Banana", "Orange")
        // val items = Array(12) { it -> it + 1 }

        // method 2:
        //val items: Array<String> = resources.getStringArray(R.array.fruit)

        // method 3: add android:entries="@array/fruit", not string, in xml file.

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items
        )

        // Method 1:
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        adapter.setDropDownViewResource(R.layout.simple_spinner_item)

        binding.spinner.adapter = adapter

        // 設定項目選擇監聽器 (Listener)
        binding.spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // 當選擇一個項目時被呼叫
                val selectedItem = parent.getItemAtPosition(position).toString()
                binding.tvResult.setText("選中結果: $selectedItem")
                Toast.makeText(this@MainActivity, "選擇了: $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 當沒有選擇任何項目時被呼叫 (例如：下拉列表關閉，但未做任何選擇)
            }
        })
    }

 */