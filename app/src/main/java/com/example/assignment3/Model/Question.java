package com.example.assignment3.Model;

import java.util.ArrayList;

public class Question {

    public String question, correct_answer, answer;
    public ArrayList <String> incorrect_answers;

    public Question(){}


    public Question(String question, String correct_answer, String answer, ArrayList<String> incorrect_answers) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.answer = answer;
        this.incorrect_answers = incorrect_answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
