package com.example.assignment3.App;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.Adapters.RecyclerViewAdapter;
import com.example.assignment3.Model.User;
import com.example.assignment3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    DatabaseReference reference;
    ArrayList<User> users;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Leaderboard");


        initImageBitMaps();
    }

    private void initImageBitMaps() {
        users = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = new User();

                    user.setEmail(ds.child("email").getValue().toString());
                    user.setScore(Integer.parseInt(ds.child("scores").getValue().toString()));

                    users.add(user);

                }

                Collections.sort(users, new SortbyScore());

                if (users.size() != 0) {
                    mImageUrls.add("https://middle.pngfans.com/20190523/ji/first-place-medal-png-gold-medal-clipart-1ea53ffa0957e40f.jpg");
                    mNames.add(users.get(0).getEmail()+ "\nTop Score:  " +users.get(0).getScores());

                    mImageUrls.add("https://p7.hiclipart.com/preview/657/437/185/gold-medal-silver-medal-clip-art-silver-medal-png-clip-art.jpg");
                    mNames.add(users.get(1).getEmail()+ "\nTop Score:  " +users.get(1).getScores());

                    mImageUrls.add("https://cdn0.iconfinder.com/data/icons/sport-balls/512/bronze_medal.png");
                    mNames.add(users.get(2).getEmail() + "\nTop Score:  " +users.get(2).getScores());
                    initRecyclerView();


                } else {
                    mImageUrls.add("https://middle.pngfans.com/20190523/ji/first-place-medal-png-gold-medal-clipart-1ea53ffa0957e40f.jpg");
                    mNames.add(users.get(0).getEmail());

                    mImageUrls.add("https://p7.hiclipart.com/preview/657/437/185/gold-medal-silver-medal-clip-art-silver-medal-png-clip-art.jpg");
                    mNames.add("Second @ place");

                    mImageUrls.add("https://cdn0.iconfinder.com/data/icons/sport-balls/512/bronze_medal.png");
                    mNames.add("Third @ Place");
                    initRecyclerView();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recylerview2);
        recyclerView.setClickable(false);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
