package com.example.kirfood;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataDrink;
import com.example.kirfood.Model.DataHotdog;
import com.example.kirfood.adapter.RecyclerViewAdapterDrink;
import com.example.kirfood.adapter.RecyclerViewAdapterHotdog;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListDrink extends Activity {
    public RecyclerView recyclerView;
    public RecyclerViewAdapterDrink recyclerViewAdapterDrink;
    public RelativeLayout description;
    public Animation slideUp, slideDown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_drink);

        recyclerView = (RecyclerView) findViewById(R.id.datalist);
//        description = (RelativeLayout) findViewById(R.id.description);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DataDrink> options =
                new FirebaseRecyclerOptions.Builder<DataDrink>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("drink"), DataDrink.class)
                        .build();
        recyclerViewAdapterDrink = new RecyclerViewAdapterDrink(options);
        recyclerView.setAdapter(recyclerViewAdapterDrink);
    }
    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapterDrink.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterDrink.stopListening();
    }
}
