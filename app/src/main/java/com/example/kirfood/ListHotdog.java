package com.example.kirfood;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataHotdog;
import com.example.kirfood.adapter.RecyclerViewAdapterHotdog;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListHotdog extends Activity {
    public RecyclerView recyclerView;
    public RecyclerViewAdapterHotdog recyclerViewAdapterHotdog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_hotdog);

        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DataHotdog> options =
                new FirebaseRecyclerOptions.Builder<DataHotdog>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hotdog"), DataHotdog.class)
                        .build();
        recyclerViewAdapterHotdog = new RecyclerViewAdapterHotdog(options);
        recyclerView.setAdapter(recyclerViewAdapterHotdog);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapterHotdog.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterHotdog.stopListening();
    }
}
