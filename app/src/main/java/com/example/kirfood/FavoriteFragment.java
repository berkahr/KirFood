package com.example.kirfood;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataDrink;
import com.example.kirfood.Model.DataPopular;
import com.example.kirfood.adapter.RecyclerViewAdapterDrink;
import com.example.kirfood.adapter.RecyclerViewAdapterFavorite;
import com.example.kirfood.adapter.RecyclerViewAdapterHome;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class FavoriteFragment extends Activity {
    BottomNavigationView bottomNavigationView;
    public RecyclerView recyclerView;
    public RecyclerViewAdapterFavorite recyclerViewAdapterFavorite;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_favorite);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);

        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataPopular> options =
                new FirebaseRecyclerOptions.Builder<DataPopular>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("favorite"), DataPopular.class)
                        .build();
        recyclerViewAdapterFavorite = new RecyclerViewAdapterFavorite(options);
        recyclerView.setAdapter(recyclerViewAdapterFavorite);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.menu:
                    startActivity(new Intent(getApplicationContext(),HomeFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.favorit:
                    return true;
                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(),CartFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.promo:
                    startActivity(new Intent(getApplicationContext(),PromoFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.user:
                    startActivity(new Intent(getApplicationContext(),UserFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewAdapterFavorite.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterFavorite.stopListening();
    }
}
