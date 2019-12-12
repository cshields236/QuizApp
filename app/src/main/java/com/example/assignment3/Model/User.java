package com.example.assignment3.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class User {

    public String email;
    private int score;

    public User(){

    }
    public User( String email) {

        this.email = email;
    }


    public User(String email, int scores) {
        this.email = email;
        this.score = scores;
    }

    public int getScores() {
        return score;
    }

    public void setScore(int scores) {
        this.score = scores;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}


