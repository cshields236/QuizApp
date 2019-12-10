package com.example.assignment3.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assignment3.R;

public class ResultActivity extends AppCompatActivity {
TextView correctA, incorrectA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        correctA = findViewById(R.id.correctAns);
        incorrectA = findViewById(R.id.correctAns);
        Intent intent= getIntent();
        String correct = intent.getStringExtra("correct");
        String incorrect = intent.getStringExtra("incorrect");
        correctA.setText(correct);
        incorrectA.setText(incorrect);


    }
}
