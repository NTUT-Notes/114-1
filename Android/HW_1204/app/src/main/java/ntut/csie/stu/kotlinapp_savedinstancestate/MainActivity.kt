package ntut.csie.stu.kotlinapp_savedinstancestate

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ntut.csie.stu.kotlinapp_savedinstancestate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    val TAG: String = "SavedInstance"
    val Tag_data: String = "Data"
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_title)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 在 onCreate 中檢查 savedInstanceState 是否存在:

        if (savedInstanceState != null) {
            // 如果 savedInstanceState 不為 null，表示 Activity 非第一次重建。
            count = savedInstanceState.getInt(Tag_data, 0); // 第二個參數是預設值 (如果找不到 Data)
            Log.d(TAG, "onCreate: 恢復 count 的值: " + count);
            binding.tvCount.text = count.toString()
        } else {
            // savedInstanceState 為 null，表示這是第一次啟動。
            Log.d(TAG, "onCreate: 第一次啟動，計數從 0 開始");
        }


        binding.btnIncrement.setOnClickListener {
            count++
            binding.tvCount.text = count.toString()
        }
    }

    // press Ctrl+O to find onSaveInstanceState,
    /* onSaveInstanceState() 方法，會在 Activity 被系統銷毀前調用 (例如：螢幕旋轉、記憶體不足) */

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(Tag_data, count)
        Log.d(Tag_data, "onSaveInstanceState: 保存 count 的值: " + count);
    }

}