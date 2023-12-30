package com.example.kirfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataPopular;
import com.example.kirfood.adapter.RecyclerViewAdapterHome;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;


public class HomeFragment extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    ImageButton pilBurger, pilPizza, pilHotdog, pilDrink;
    private TextView seeAll;
    public RecyclerView recyclerView;
    public RecyclerViewAdapterHome recyclerViewAdapterHome;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        pilBurger = (ImageButton) findViewById(R.id.buttonBurger);
        pilPizza = (ImageButton) findViewById(R.id.buttonPizza);
        pilHotdog = (ImageButton) findViewById(R.id.buttonHotdog);
        pilDrink = (ImageButton) findViewById(R.id.buttonDrink);
        seeAll = (TextView) findViewById(R.id.clickSeeAll);
//bottomNavigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu);
//recycleView
        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataPopular> options =
                new FirebaseRecyclerOptions.Builder<DataPopular>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("favorite"), DataPopular.class)
                        .build();
        recyclerViewAdapterHome = new RecyclerViewAdapterHome(options);
        recyclerView.setAdapter(recyclerViewAdapterHome);

//pilBurger
        pilBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListBurger.class));
            }
        });
//pilPizza
        pilPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListPizza.class));
            }
        });
//pilHotdog
        pilHotdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListHotdog.class));
            }
        });
//pilDrink
        pilDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListDrink.class));
            }
        });
//seeAll
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FavoriteFragment.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

//        bottomNavigation
        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.menu:
                    return true;
                case R.id.favorit:
                    startActivity(new Intent(getApplicationContext(),FavoriteFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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
        recyclerViewAdapterHome.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterHome.stopListening();
    }
}
