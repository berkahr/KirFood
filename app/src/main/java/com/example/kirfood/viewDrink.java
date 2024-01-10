package com.example.kirfood;

import static com.example.kirfood.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class viewDrink extends AppCompatActivity {

    public ImageView imgDrink;
    public RelativeLayout description;
    public Animation slideUp, slideDown;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.view_drink);
        description = findViewById(id.description);
        imgDrink = (ImageView) findViewById(id.imgDrinkMenu);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), anim.slide_up);

        imgDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DescriptionDrink.class));
            }
        });
    }
}
