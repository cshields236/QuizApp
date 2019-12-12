package com.example.assignment3.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    public String email;
    private ArrayList<Integer> scores;

    public User(){

    }
    public User( String email) {

        this.email = email;
    }


    public User(String email, ArrayList<Integer> scores) {
        this.email = email;
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScore(ArrayList<Integer> scores) {
        this.scores = scores;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
