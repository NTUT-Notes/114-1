package com.yfhd.midterm_113590021;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Score {
    String name;
    int[] score;

    public Score(String name, int[] score) {
        this.name = name;
        this.score = score;
    }

    public static Score fromString(String data) {
        String[] parse = data.split(" ");

        int[] score = new int[5];

        for (int i=0; i<5; i++) {
            score[i] = Integer.parseInt(parse[i+1]);
        }

        return new Score( parse[0], score);
    }

    public static Score[] fromMultipleString(String data) {
        String[] parse = data.split("\n");

        Score[] scores = new Score[parse.length];
        for (int i=0; i<parse.length; i++) {
            scores[i] = Score.fromString(parse[i]);
        }

        return scores;
    }

    public int getSumScore() {
        int tmp = 0;
        for (var i : this.score) {
            tmp += i;
        }
        return tmp;
    }

    @SuppressLint("DefaultLocale")
    public String showIndexAnnoted(int index) {
        StringBuilder buf = new StringBuilder();

        buf.append(this.name);
        buf.append(" ");

        for (int i=0; i<this.score.length; i++) {
            if (i == index) {
                buf.append(String.format("(%d) ", this.score[i]));
            } else {
                buf.append(String.format("%d ", this.score[i]));
            }
        }

        return buf.toString();
    }

    @SuppressLint("DefaultLocale")
    public String showWithSum() {
        StringBuilder buf = new StringBuilder();

        buf.append(this.name);
        buf.append(" ");

        for (int j : this.score) {
            buf.append(String.format("%d ", j));
        }

        buf.append(String.format("=> %d", getSumScore()));

        return buf.toString();
    }

    @SuppressLint("DefaultLocale")
    public String showNormal() {
        StringBuilder buf = new StringBuilder();

        buf.append(this.name);
        buf.append(" ");

        for (int j : this.score) {
            buf.append(String.format("%d ", j));
        }

        return buf.toString();
    }
}

public class MainActivity extends AppCompatActivity {
    String rawData =
        "成婉財 27 91 21 33 13\n" +
        "翁雅婷 96 90 40 55 69\n" +
        "袁維茹 38 85 72 13 34\n" +
        "黃士哲 81 40 24 93 79\n" +
        "郭珮珊 72 33 32 83 73\n" +
        "陳儀琬 78 55 22 41 62\n" +
        "李碧彥 30 48 13 93 70\n" +
        "梁健玉 23 89 10 44 24\n" +
        "許雅淑 90 11 33 27 67\n" +
        "蕭宛新 29 64 64 90 43\n" +
        "邱冠勛 81 64 52 73 98";

    Score[] scores = Score.fromMultipleString(rawData);
    int currentIndex = 0;

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

        addButtonListener();
    }

    public void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // 'this' refers to the current Activity

        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Code to execute when the "OK" button is clicked
                    // e.g., perform an action, dismiss the dialog
                    dialog.dismiss();
                }
            });
        builder.show();
    }

    public void addButtonListener() {
        TextView resultTextView = (TextView) findViewById(R.id.result_view);

        // Show All Score Button
        Button showAllButton = (Button) findViewById(R.id.show_all_button);
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder buf = new StringBuilder();
                for (Score i : scores) {
                    buf.append( String.format("%s\n", i.showNormal()) );
                }
                resultTextView.setText(buf.toString());
            }
        });

        Button firstButton = (Button) findViewById(R.id.first_button);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 0;
                resultTextView.setText(scores[currentIndex].showWithSum());
            }
        });

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex >= scores.length - 1) {
                    Toast.makeText(
                MainActivity.this, "已到達最後一筆！", Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                currentIndex += 1;
                resultTextView.setText(scores[currentIndex].showWithSum());
            }
        });

        Button prevButton = (Button) findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex <= 0) {
                    Toast.makeText(
                            MainActivity.this, "已到達第一筆！", Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                currentIndex -= 1;
                resultTextView.setText(scores[currentIndex].showWithSum());
            }
        });

        Button lastButton = (Button) findViewById(R.id.last_button);
        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = scores.length - 1;
                resultTextView.setText(scores[currentIndex].showWithSum());
            }
        });

        // Sort Functions
        EditText targetSubject = (EditText) findViewById(R.id.target_input);
        Button sortButton = (Button) findViewById(R.id.sort_button);
        CheckBox showAllCheckbox = (CheckBox) findViewById(R.id.showall_checkbox);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String subjectIndex = targetSubject.getText().toString();

                if (subjectIndex.isEmpty()) {
                    showAlert("來自 邱冠勛 的提醒!", "請先輸入科目！");
                    return;
                }

                int selIndex;
                try {
                    selIndex = Integer.parseInt(subjectIndex);
                } catch (Exception e) {
                    showAlert("來自 邱冠勛 的提醒!", "無此科目！");
                    return;
                }

                selIndex -= 1; // Convert to zero based

                if (selIndex < 0 || selIndex >= scores[0].score.length) {
                    showAlert("來自 邱冠勛 的提醒!", "無此科目！");
                    return;
                }

                boolean allScore = showAllCheckbox.isChecked();

                // Initial the new sortable array
                List<Score> tmpArray = new ArrayList<Score>();
                Collections.addAll(tmpArray, scores);
                int finalSelIndex = selIndex;
                tmpArray.sort( (Score a, Score b)
                        -> a.score[finalSelIndex] > b.score[finalSelIndex] ? -1 : 1);

                StringBuilder buf = new StringBuilder();
                buf.append( String.format("第 %d 科的成績排序:\n", selIndex+1) );
                for (Score s : tmpArray) {
                    if (allScore) {
                        buf.append(String.format("%s\n", s.showIndexAnnoted(selIndex)));
                    } else {
                        buf.append(String.format("%s %d\n", s.name, s.score[selIndex]));
                    }
                }

                resultTextView.setText(buf.toString());
            }
        });
    }
}