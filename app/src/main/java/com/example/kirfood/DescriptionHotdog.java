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
import com.example.kirfood.Model.DataDrink;
import com.example.kirfood.Model.DataHotdog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DescriptionHotdog extends Activity {
    public ImageView ImgDesDrink;
    public TextView NameDesDrink, DesDrink, PriceDesDrink;
    public Button Share, AddCart, OrderNow;
    String HotdogId="";
    FirebaseDatabase database;
    DatabaseReference hotdog, reference;
    DataHotdog currentHotdog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_description_hotdog);
        database = FirebaseDatabase.getInstance();
        hotdog = database.getReference("hotdog").child("kirbyChicken");

        ImgDesDrink = (ImageView) findViewById(R.id.imgDesHotdog);
        NameDesDrink = (TextView) findViewById(R.id.nameDesHotdog);
        DesDrink = (TextView) findViewById(R.id.DesHotdog);
        PriceDesDrink = (TextView) findViewById(R.id.priceDesHotdog);
        Share = (Button) findViewById(R.id.ShareDesHotdog);
        AddCart = (Button) findViewById(R.id.AddDesHotdog);
        OrderNow = (Button) findViewById(R.id.FavoriteDesHotdog);

        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("favorite");
                String favorite = reference.push().getKey();
                HashMap<String,String> parameter = new HashMap<>();
                parameter.put("name","Kirby Chicken");
                parameter.put("shortDes","Chicken hotdog");
                parameter.put("img","https://img.freepik.com/free-photo/hot-dog_144627-19564.jpg?size=626&ext=jpg");
                parameter.put("price","3");
                reference.child(favorite).setValue(parameter);
                Toast.makeText(DescriptionHotdog.this,"Added To favorite",Toast.LENGTH_SHORT).show();
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
        getDetailDrink(HotdogId);
    }

    private void getDetailDrink(String hotdogId) {
        hotdog.child(hotdogId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentHotdog = snapshot.getValue(DataHotdog.class);
                Glide.with(getBaseContext()).load(currentHotdog.getImg()).into(ImgDesDrink);
                NameDesDrink.setText(currentHotdog.getName());
                DesDrink.setText(currentHotdog.getLongDes());
                PriceDesDrink.setText(currentHotdog.getPrice());
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
        parameter.put("name","Kirby Chicken");
        parameter.put("price","8");
        reference.child(CartId).setValue(parameter);
        Toast.makeText(DescriptionHotdog.this,"Added to Cart", Toast.LENGTH_SHORT).show();
    }
}
