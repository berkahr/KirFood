package com.example.kirfood;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataPizza;
import com.example.kirfood.adapter.RecyclerViewAdapterPizza;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListPizza extends Activity {
    public RecyclerView recyclerView;
    public RecyclerViewAdapterPizza recyclerViewAdapterPizza;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pizza);

        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DataPizza> options =
                new FirebaseRecyclerOptions.Builder<DataPizza>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pizza"), DataPizza.class)
                        .build();
        recyclerViewAdapterPizza = new RecyclerViewAdapterPizza(options);
        recyclerView.setAdapter(recyclerViewAdapterPizza);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapterPizza.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterPizza.stopListening();
    }
}
