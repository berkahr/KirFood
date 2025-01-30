package com.example.kirfood;

import android.annotation.SuppressLint;
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
import com.example.kirfood.Model.DataDrink;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DescriptionDrink extends Activity {
    public ImageView ImgDesDrink;
    public TextView NameDesDrink, DesDrink, PriceDesDrink;
    public Button Share, AddCart, OrderNow;
    String DrinkId="";
    FirebaseDatabase database;
    DatabaseReference drink, reference;
    DataDrink currentDrink;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_description_drink);
        //database
        database = FirebaseDatabase.getInstance();
        drink = database.getReference("drink").child("kirbyBlue");

        ImgDesDrink = (ImageView) findViewById(R.id.imgDesDrink);
        NameDesDrink = (TextView) findViewById(R.id.nameDesDrink);
        DesDrink = (TextView) findViewById(R.id.DesDrink);
        PriceDesDrink = (TextView) findViewById(R.id.priceDesDrink);
        Share = (Button) findViewById(R.id.ShareDesDrink);
        AddCart = (Button) findViewById(R.id.AddDesDrink);
        OrderNow = (Button) findViewById(R.id.FavoriteDesDrink);

        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("favorite");
                String favorite = reference.push().getKey();
                HashMap<String,String> parameter = new HashMap<>();
                parameter.put("name","Kirby Blue");
                parameter.put("shortDes","blue Ocean");
                parameter.put("img","https://img.freepik.com/free-photo/blue-cocktail-with-orange_144627-18407.jpg?size=626&ext=jpg");
                parameter.put("price","8");
                reference.child(favorite).setValue(parameter);
                Toast.makeText(DescriptionDrink.this,"Added To favorite",Toast.LENGTH_SHORT).show();
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
        getDetailDrink(DrinkId);
    }

    private void addToCart() {
        reference = FirebaseDatabase.getInstance().getReference("cart");
        String CartId = reference.push().getKey();
        HashMap<String,String> parameter = new HashMap<>();
        parameter.put("name","Kirby Blue");
        parameter.put("price","8");
        reference.child(CartId).setValue(parameter);
        Toast.makeText(DescriptionDrink.this,"Added to Cart", Toast.LENGTH_SHORT).show();
    }

    private void getDetailDrink(String drinkId) {
        drink.child(drinkId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentDrink = snapshot.getValue(DataDrink.class);
                Glide.with(getBaseContext()).load(currentDrink.getImg()).into(ImgDesDrink);
                NameDesDrink.setText(currentDrink.getName());
                DesDrink.setText(currentDrink.getLongDes());
                PriceDesDrink.setText(currentDrink.getPrice());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
