package com.example.kirfood;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.kirfood.Model.DataBurger;
import com.example.kirfood.Model.DataDrink;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DescriptionBurger extends Activity {
    public ImageView ImgDesDrink;
    public TextView NameDesDrink, DesDrink, PriceDesDrink;
    public Button Share, AddCart, OrderNow,favorite;
    String BurgerId="";
    FirebaseDatabase database;
    DatabaseReference burger, reference;
    DataBurger currentBurger;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_description_burger);
        database = FirebaseDatabase.getInstance();
        burger = database.getReference("burger").child("kirbyBeef");

        ImgDesDrink = (ImageView) findViewById(R.id.imgDesBurger);
        NameDesDrink = (TextView) findViewById(R.id.nameDesBurger);
        DesDrink = (TextView) findViewById(R.id.DesBurger);
        PriceDesDrink = (TextView) findViewById(R.id.priceDesBurger);
        Share = (Button) findViewById(R.id.ShareDesBurger);
        AddCart = (Button) findViewById(R.id.AddDesBurger);
        OrderNow = (Button) findViewById(R.id.FavoriteDesBurger);
        favorite = (Button) findViewById(R.id.FavoriteDesBurger);
//        favorite
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("favorite");
                String favorite = reference.push().getKey();
                HashMap<String,String> parameter = new HashMap<>();
                parameter.put("name","Kirby Beef");
                parameter.put("shortDes","beef burger");
                parameter.put("img","https://img.freepik.com/free-photo/tasty-burger-isolated-white-background-fresh-hamburger-fastfood-with-beef-cheese_90220-1063.jpg?size=626&ext=jpg");
                parameter.put("price","6");
                reference.child(favorite).setValue(parameter);
                Toast.makeText(DescriptionBurger.this,"Added To favorite",Toast.LENGTH_SHORT).show();
            }
        });
        //        add to cart
        AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
        //get drinkid
//        if (getIntent() != null)
//            DrinkId = getIntent().getStringExtra("DrinkId");
//        if (!DrinkId.isEmpty()){
//            getDetailDrink(DrinkId);
//        }
        getDetailBurger(BurgerId);
    }

    private void getDetailBurger(String burgerId) {
        burger.child(burgerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentBurger = snapshot.getValue(DataBurger.class);
                Glide.with(getBaseContext()).load(currentBurger.getImg()).into(ImgDesDrink);
                NameDesDrink.setText(currentBurger.getName());
                DesDrink.setText(currentBurger.getLongDes());
                PriceDesDrink.setText(currentBurger.getPrice());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addToCart() {
        reference = FirebaseDatabase.getInstance().getReference("cart");
        String CartId = reference.push().getKey();
        HashMap<String,String> parameter = new HashMap<>();
        parameter.put("name","Kirby Beef");
        parameter.put("price","6");
        reference.child(CartId).setValue(parameter);
        Toast.makeText(DescriptionBurger.this,"Added to Cart", Toast.LENGTH_SHORT).show();
    }
}
