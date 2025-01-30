package com.example.kirfood;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirfood.Model.DataCart;
import com.example.kirfood.adapter.RecyclerViewAdapterCart;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartFragment extends Activity {
    public Button alamat, order, metode;
    public TextView priceInt, taxInt, otherInt, totalInt;
    public RecyclerView recyclerView;
    public RecyclerViewAdapterCart recyclerViewAdapterCart;
    FirebaseDatabase database;
    DatabaseReference cart;
    DataCart dataCart;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);
        //database
        database = FirebaseDatabase.getInstance();
        cart = database.getReference("cart");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);
        alamat = (Button) findViewById(R.id.ButtonAddress);
        order = (Button) findViewById(R.id.orderNow);
        metode = (Button) findViewById(R.id.metode);
        priceInt = (TextView) findViewById(R.id.priceInt);
        taxInt = (TextView) findViewById(R.id.taxInt);
        otherInt = (TextView) findViewById(R.id.otherInt);
        totalInt = (TextView) findViewById(R.id.totalInt);

        recyclerView = (RecyclerView)findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataCart> options =
                new FirebaseRecyclerOptions.Builder<DataCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cart"), DataCart.class)
                        .build();
        recyclerViewAdapterCart = new RecyclerViewAdapterCart(options);
        recyclerView.setAdapter(recyclerViewAdapterCart);

        getSummaryCart();
        metode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Payment.class));
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartFragment.this,"Your order is in progress",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeFragment.class));
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

    private void getSummaryCart() {
        cart.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataCart = snapshot.getValue(DataCart.class);
                priceInt.setText("$53");
                taxInt.setText("1.0%");
                otherInt.setText("$0");
                totalInt.setText("$47,7");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
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
