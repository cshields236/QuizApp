package com.example.assignment3.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.assignment3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    TextView dbhead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dbhead  =findViewById(R.id.dbhead);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String email = currentUser.getEmail();
        String[] parts = email.split("@");
        String part1 = parts[0];
        dbhead.setText(part1 + "'s Dashboard");
    }


    public void openQuiz(View view){
        Intent i = new Intent(this, QuizOptions.class);
        startActivity(i);
    }

    public void logOut(View view){
        finish();
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
    }

    public void showLeaderboard(View view){
        finish();
        Intent i = new Intent(this, LeaderboardActivity.class);
        startActivity(i);
    }
}
