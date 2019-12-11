package com.example.assignment3.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    ImageView confirm, nextQ;
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
        nextQ = findViewById(R.id.button_next);
        timer = findViewById(R.id.text_timer);

        saveUserInfo();

        updateQuestion();


    }

    private void updateQuestion() {
        total++;
        Intent i = getIntent();
        catChoice =  i.getStringExtra("CatChoice");

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

                        options.add(ds.child("incorrect_answers").child("0").getValue().toString());
                        options.add(ds.child("incorrect_answers").child("1").getValue().toString());
                        options.add(ds.child("incorrect_answers").child("2").getValue().toString());
                        options.add(q.getCorrect_answer());


                        q.setIncorrect_answers(options);


                        questionsList.add(q);

                       // Log.d(TAG, "onDataChange: " + q.getCategory());

                    }
                    //Shuffling the array to get a random question
                    Collections.shuffle(questionsList);
                    for (Question q : questionsList) {
                        if (q.getCategory().contains(catChoice)) {
                            ArrayList<String> options = new ArrayList<>();
                            options.add(q.getIncorrect_answers().get(0));
                            options.add(q.getIncorrect_answers().get(1));
                            options.add(q.getIncorrect_answers().get(2));
                            options.add(q.getCorrect_answer());



                            //Setting Textviews with the values from the database
                            questionTxt.setText(q.question);

                            Log.d(TAG, "onDataChange: " + options.get(0) + options.get(1)+ options.get(2));
                            //Shuffling answers list so its a random selection each time the app is run
                            Collections.shuffle(options);

                            option1.setText(options.get(0));
                            option2.setText(options.get(1));
                            option3.setText(options.get(2));
                            option4.setText(options.get(3));

                            correctAns = (q.getCorrect_answer());

                            ProgressBar progressBar = findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.INVISIBLE);
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
//                    option1.setChecked(false);
//                    option2.setChecked(false);
//                    option3.setChecked(false);
//                    option4.setChecked(false);



                    int radId = group.getCheckedRadioButtonId();
                    selected = findViewById(radId);

                    if (checkAnswer().equals(correctAns)) {

                        selected.setBackgroundColor(Color.GREEN);

                        Toast.makeText(MainActivity.this, "Well done", Toast.LENGTH_LONG).show();

                        correct++;
                    } else {

                        selected.setBackgroundColor(Color.RED);

                       int o1 = group.getChildAt(0).getId();
                        int o2 = group.getChildAt(1).getId();
                        int o3 = group.getChildAt(2).getId();
                        int o4 = group.getChildAt(3).getId();

                        RadioButton p1 = findViewById(o1);
                        RadioButton p2 = findViewById(o2);
                        RadioButton p3 = findViewById(o3);
                        RadioButton p4 = findViewById(o4);
                        if(correctAns.equalsIgnoreCase(p1.getText().toString())){
                            p1.setBackgroundColor(Color.GREEN);
                        }else if ( correctAns.equalsIgnoreCase(p2.getText().toString())){
                            p2.setBackgroundColor(Color.GREEN);
                        }
                        else if ( correctAns.equalsIgnoreCase(p3.getText().toString())){
                            p3.setBackgroundColor(Color.GREEN);
                        }
                        else if ( correctAns.equalsIgnoreCase(p4.getText().toString())){
                            p4.setBackgroundColor(Color.GREEN);
                        }
                        Toast.makeText(MainActivity.this,"Unlucky - Correct Answer was: " +  correctAns, Toast.LENGTH_LONG).show();
                        wrong++;
                    }

                }

            });

            nextQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateQuestion();

                    option1.setBackgroundColor(Color.rgb(49, 148, 252));
                    option2.setBackgroundColor(Color.rgb(49, 148, 252));
                    option3.setBackgroundColor(Color.rgb(49, 148, 252));
                    option4.setBackgroundColor(Color.rgb(49, 148, 252));

                    option1.setChecked(false);
                    option2.setChecked(false);
                    option3.setChecked(false);
                    option4.setChecked(false);
                }
            });

            view_score.setText("Score: " + correct);
        }

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
    private void openResult() {

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("correct", String.valueOf(correct));
        intent.putExtra("incorrect", String.valueOf(wrong));
        startActivity(intent);
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
