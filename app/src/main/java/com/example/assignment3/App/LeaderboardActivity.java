package com.example.assignment3.App;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.Adapters.RecyclerViewAdapter;
import com.example.assignment3.R;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Leaderboard");


        initImageBitMaps();
    }

    private void initImageBitMaps() {

        mImageUrls.add("https://middle.pngfans.com/20190523/ji/first-place-medal-png-gold-medal-clipart-1ea53ffa0957e40f.jpg");
        mNames.add("First Place");

        mImageUrls.add("https://p7.hiclipart.com/preview/657/437/185/gold-medal-silver-medal-clip-art-silver-medal-png-clip-art.jpg");
        mNames.add("Second place");

        mImageUrls.add("https://cdn0.iconfinder.com/data/icons/sport-balls/512/bronze_medal.png");
        mNames.add("Third Place");


        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recylerview2);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
