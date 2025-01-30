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
import com.example.kirfood.Model.DataPizza;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DescriptionPizza extends Activity {
    public ImageView ImgDesDrink;
    public TextView NameDesDrink, DesDrink, PriceDesDrink;
    public Button Share, AddCart, OrderNow;
    String PizzaId="";
    FirebaseDatabase database;
    DatabaseReference pizza, reference;
    DataPizza currentPizza;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_description_pizza);

        database = FirebaseDatabase.getInstance();
        pizza = database.getReference("pizza").child("kirbyClassic");

        ImgDesDrink = (ImageView) findViewById(R.id.imgDesPizza);
        NameDesDrink = (TextView) findViewById(R.id.nameDesPizza);
        DesDrink = (TextView) findViewById(R.id.DesPizza);
        PriceDesDrink = (TextView) findViewById(R.id.priceDesPizza);
        Share = (Button) findViewById(R.id.ShareDesPizza);
        AddCart = (Button) findViewById(R.id.AddDesPizza);
        OrderNow = (Button) findViewById(R.id.FavoriteDesPizza);

        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("favorite");
                String favorite = reference.push().getKey();
                HashMap<String,String> parameter = new HashMap<>();
                parameter.put("name","Kirby Classic");
                parameter.put("shortDes","mushroom pizza");
                parameter.put("img","https://img.freepik.com/free-photo/fresh-pizza-with-mushrooms-ham-cheese-white-background-copy-space-homemade-with-love-fast-delivery-recipe-menu-top-view_639032-297.jpg?size=626&ext=jpg");
                parameter.put("price","25");
                reference.child(favorite).setValue(parameter);
                Toast.makeText(DescriptionPizza.this,"Added To favorite",Toast.LENGTH_SHORT).show();
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
        getDetailDrink(PizzaId);
    }

    private void getDetailDrink(String pizzaId) {
        pizza.child(pizzaId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentPizza = snapshot.getValue(DataPizza.class);
                Glide.with(getBaseContext()).load(currentPizza.getImg()).into(ImgDesDrink);
                NameDesDrink.setText(currentPizza.getName());
                DesDrink.setText(currentPizza.getLongDes());
                PriceDesDrink.setText(currentPizza.getPrice());
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
        parameter.put("name","Kirby Classic");
        parameter.put("price","25");
        reference.child(CartId).setValue(parameter);
        Toast.makeText(DescriptionPizza.this,"Added to Cart", Toast.LENGTH_SHORT).show();
    }
}
