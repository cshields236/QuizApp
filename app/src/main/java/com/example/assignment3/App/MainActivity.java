package com.example.assignment3.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.Model.Question;
import com.example.assignment3.Model.User;
import com.example.assignment3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    int total = 0;
    int correct = 0;
    int wrong = 0;

    String catChoice;
    TextView questionTxt, question_count, view_score;
    RadioGroup group;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    RadioButton selected;
    Button confirm;
    DatabaseReference reference;
    String correctAns;
    TextView timer;
    FirebaseUser firebaseUser;
    ArrayList<Question> questionsList;
    final String TAG = "Firebase ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionTxt = findViewById(R.id.text_view_question);
        option1 = findViewById(R.id.radio_button1);
        option2 = findViewById(R.id.radio_button2);
        option3 = findViewById(R.id.radio_button3);
        option4 = findViewById(R.id.radio_button4);
        question_count = findViewById(R.id.text_view_question_count);
        view_score = findViewById(R.id.text_view_score);
        group = findViewById(R.id.radio_group);
        confirm = findViewById(R.id.button_confirm);
        timer = findViewById(R.id.text_timer);

        saveUserInfo();

        updateQuestion();


    }

    private void updateQuestion() {
        total++;

        catChoice = "sports";
        question_count.setText(String.format("Question %s/5", String.valueOf(total)));
        if (total > 5) {
            //Open Result Activity
            openResult();
        } else {
            questionsList = new ArrayList<>();
            reference = FirebaseDatabase.getInstance().getReference().child("questions");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ArrayList<String> options = new ArrayList<>();
                        Question q = new Question();
                        q.setQuestion(ds.child("question").getValue().toString());
                        q.setCategory(ds.child("category").getValue().toString());
                        q.setCorrect_answer(ds.child("correct_answer").getValue().toString());
                        options.add(ds.child("correct_answer").getValue().toString());
                        options.add(ds.child("incorrect_answers").child("0").getValue().toString());
                        options.add(ds.child("incorrect_answers").child("1").getValue().toString());
                        options.add(ds.child("incorrect_answers").child("2").getValue().toString());
                        q.setIncorrect_answers(options);
                        questionsList.add(q);

                       // Log.d(TAG, "onDataChange: " + q.getCategory());

                    }
                    //Shuffling the array to get a random question
                    Collections.shuffle(questionsList);
                    for (Question q : questionsList) {
                        if (q.getCategory().equalsIgnoreCase(catChoice)) {
                            ArrayList<String> options = new ArrayList<>();
                            options.add(q.getIncorrect_answers().get(0));
                            options.add(q.getIncorrect_answers().get(1));
                            options.add(q.getIncorrect_answers().get(2));
                            options.add(q.getCorrect_answer());

                            //Setting Textviews with the values from the database
                            questionTxt.setText(q.question);

                            //Shuffling answers list so its a random selection each time the app is run
                            Collections.shuffle(options);

                            option1.setText(options.get(0));
                            option2.setText(options.get(1));
                            option3.setText(options.get(2));
                            option4.setText(options.get(3));

                            correctAns = (q.getCorrect_answer());

                            Log.d(TAG, "Right answer: " + q.correct_answer);

                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);

                    if (checkAnswer().equals(correctAns)) {


                        Toast.makeText(MainActivity.this, "Well done", Toast.LENGTH_LONG).show();

                        correct++;
                    } else {
                        Toast.makeText(MainActivity.this, "You silly ", Toast.LENGTH_LONG).show();
                        wrong++;
                    }
                    updateQuestion();
                }

            });

            view_score.setText("Score: " + correct);
        }

    }

    private void openResult() {

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("correct", String.valueOf(correct));
        intent.putExtra("incorrect", String.valueOf(wrong));
        startActivity(intent);
    }

    public String checkAnswer() {

        int radId = group.getCheckedRadioButtonId();
        selected = findViewById(radId);
        String guess;
        if (selected.getText() != null) {
            guess = (String) selected.getText();
        } else {
            guess = "nah";
        }
        return guess;
    }

    public void saveUserInfo() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        String email = currentUser.getEmail();
        reference = FirebaseDatabase.getInstance().getReference();

        User user = new User(email);
        reference.child(currentUser.getUid()).setValue(user);
    }
}
