package com.example.kirfood;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataBurger;
import com.example.kirfood.adapter.RecyclerViewAdapterBurger;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListBurger extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerViewAdapterBurger recyclerViewAdapterBurger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_burger);

        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DataBurger> options =
                new FirebaseRecyclerOptions.Builder<DataBurger>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("burger"), DataBurger.class)
                        .build();
        recyclerViewAdapterBurger = new RecyclerViewAdapterBurger(options);
        recyclerView.setAdapter(recyclerViewAdapterBurger);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapterBurger.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterBurger.stopListening();
    }
}
