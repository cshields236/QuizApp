package com.example.assignment3.App;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.example.assignment3.Adapters.RecyclerViewAdapter;
import com.example.assignment3.R;

import java.util.ArrayList;

public class QuizOptions extends AppCompatActivity {

    private static final String TAG = "QuizOptions";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        Log.d(TAG, "onCreate: started");
        initImageBitMaps();
    }

    private void initImageBitMaps() {

        mImageUrls.add("https://img.maximummedia.ie/joe_ie/eyJkYXRhIjoie1widXJsXCI6XCJodHRwOlxcXC9cXFwvbWVkaWEtam9lLm1heGltdW1tZWRpYS5pZS5zMy5hbWF6b25hd3MuY29tXFxcL3dwLWNvbnRlbnRcXFwvdXBsb2Fkc1xcXC8yMDE4XFxcLzAyXFxcLzA3MTMwMTI3XFxcL3F1aXptYXJrLmpwZ1wiLFwid2lkdGhcIjo2NDAsXCJoZWlnaHRcIjozNjAsXCJkZWZhdWx0XCI6XCJodHRwczpcXFwvXFxcL3d3dy5qb2UuaWVcXFwvYXNzZXRzXFxcL2ltYWdlc1xcXC9qb2VcXFwvbm8taW1hZ2UucG5nP2lkPTI2NGEyZGJlMzcwZjJjNjc1ZmNkXCIsXCJvcHRpb25zXCI6W119IiwiaGFzaCI6IjU4YWIyYzNjZjllYjRlZDgwYTFmZTQ4NzkwZWVhNmFiODc1ZmIyNDUifQ==/quizmark.jpg");
        mNames.add("General Knowledge");

        mImageUrls.add("https://imagesvc.timeincapp.com/v3/mm/image?url=https%3A%2F%2Fcdn-s3.si.com%2Fs3fs-public%2Fimages%2F8-muhammad-ali-1965-fs.jpg&w=800&q=85");
        mNames.add("Sports");

        mImageUrls.add("https://media.nature.com/lw800/magazine-assets/d41586-018-07684-4/d41586-018-07684-4_16331684.jpg");
        mNames.add("Science");

        mImageUrls.add("https://images.immediate.co.uk/production/volatile/sites/7/2018/01/Military-History_GettyImages-50691729-875e251.jpg?quality=45&resize=620,413");
        mNames.add("History");

        mImageUrls.add("https://www.aljazeera.com/mritems/imagecache/mbdxxlarge/mritems/Images/2019/12/6/891769d9f2e44fd0a6ded8df99e2f52a_18.jpg");
        mNames.add("Politics");

        mImageUrls.add("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Frobcain%2Ffiles%2F2017%2F10%2FKevin-Home-Alone.jpg");
        mNames.add("Film");

        mImageUrls.add("https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80");
        mNames.add("Books");

        mImageUrls.add("https://d2s36jztkuk7aw.cloudfront.net/sites/default/files/tile/image/original_441.jpg");
        mNames.add("Music");
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recylerview);
        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
