package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int total = 0;
    int correct = 0;
    int wrong = 0;

    TextView questionTxt, question_count;
    RadioGroup group;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;
    RadioButton selected;
    Button confirm;
    DatabaseReference qreference;
    String correctAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionTxt =  findViewById(R.id.text_view_question);
        option1 = findViewById(R.id.radio_button1);
        option2 = findViewById(R.id.radio_button2);
        option3 = findViewById(R.id.radio_button3);
        option4 = findViewById(R.id.radio_button4);
        question_count = findViewById(R.id.text_view_question_count);
        group = findViewById(R.id.radio_group);
        confirm = findViewById(R.id.button_confirm);
        TextView timer = findViewById(R.id.text_timer);

        updateQuestion();
    }

    private void updateQuestion() {
        total++;
        question_count.setText(String.format("Question %s/5",  String.valueOf(total)));
        if (total > 4){
            //Open Result Activity

        }
        else{
            qreference = FirebaseDatabase.getInstance().getReference().child("results").child(String.valueOf(total));

            qreference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Question question = dataSnapshot.getValue(Question.class);

                    ArrayList<String> options = new ArrayList<>();

                    options.add(question.correct_answer);
                    options.add(question.incorrect_answers.get(0));
                    options.add(question.incorrect_answers.get(1));
                    options.add(question.incorrect_answers.get(2));
                    questionTxt.setText(question.question);

                    Collections.shuffle(options);


                        option1.setText(options.get(0));
                        option2.setText(options.get(1));
                        option3.setText(options.get(2));
                        option4.setText(options.get(3));

                    correctAns = (question.getCorrect_answer());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });


            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                    if (checkAnswer().equals(correctAns)) {
                        Toast.makeText(MainActivity.this, "Well done", Toast.LENGTH_LONG).show();

                        correct++;
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You silly ", Toast.LENGTH_LONG).show();

                    }
                }
            });


        }

    }

    public String checkAnswer(){

        int radId = group.getCheckedRadioButtonId();
        selected = findViewById(radId);
        String guess;
        if (selected.getText() != null) {
             guess = (String) selected.getText();
        }
        else{
             guess = "nah";
        }
        return guess;
    }
}
