package com.example.kirfood;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataCart;
import com.example.kirfood.Model.DataPopular;
import com.example.kirfood.adapter.RecyclerViewAdapterCart;
import com.example.kirfood.adapter.RecyclerViewAdapterFavorite;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class CartFragment extends Activity {
    public Button alamat, order;
    public RecyclerView recyclerView;
    public RecyclerViewAdapterCart recyclerViewAdapterCart;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        alamat = (Button) findViewById(R.id.ButtonAddress);
        order = (Button) findViewById(R.id.orderNow);

        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataCart> options =
                new FirebaseRecyclerOptions.Builder<DataCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cart"), DataCart.class)
                        .build();
        recyclerViewAdapterCart = new RecyclerViewAdapterCart(options);
        recyclerView.setAdapter(recyclerViewAdapterCart);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });
        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });
        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.menu:
                    startActivity(new Intent(getApplicationContext(),HomeFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.favorit:
                    startActivity(new Intent(getApplicationContext(),FavoriteFragment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.cart:
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
        recyclerViewAdapterCart.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerViewAdapterCart.stopListening();
    }
}
