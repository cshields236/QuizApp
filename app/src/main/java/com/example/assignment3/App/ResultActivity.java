package com.example.assignment3.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.assignment3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResultActivity extends AppCompatActivity {
TextView correctA, incorrectA, head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        correctA = findViewById(R.id.correctAns);
        incorrectA = findViewById(R.id.incAns);
        head = findViewById(R.id.textView4);
        Intent intent= getIntent();


        String correct = intent.getStringExtra("correct");
        String incorrect = intent.getStringExtra("incorrect");
        correctA.setText(correct);
        incorrectA.setText(incorrect);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String email = currentUser.getEmail();
        String[] parts = email.split("@");
        String part1 = parts[0];


        head.setText(part1 + "'s Score" );


    }

    public void returnToMenu(View view){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
