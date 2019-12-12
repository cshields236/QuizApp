package com.example.assignment3.App;

import com.example.assignment3.Model.User;

import java.util.Comparator;

class SortbyScore implements Comparator<User>
{

    @Override
    public int compare(User o1, User o2) {
        return   o2.getScores() -o1.getScores();
    }
}