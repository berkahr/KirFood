package com.example.kirfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFragment extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    public ImageButton profile,payment,information,contact;
    public TextView name;
    public Button Logout;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        payment = (ImageButton) findViewById(R.id.payment);
        profile = (ImageButton) findViewById(R.id.profile);
        information = (ImageButton) findViewById(R.id.information);
        contact =(ImageButton) findViewById(R.id.contactUs);
        Logout = (Button) findViewById(R.id.LogOut);
        name=(TextView)findViewById(R.id.userName);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.user);

        if (firebaseUser!=null){
            name.setText(firebaseUser.getDisplayName());
        }else {
            name.setText("User");
        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContactUs.class));
                finish();
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AboutUs.class));
                finish();
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Payment.class));
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserProfile.class));
                finish();
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
                    return true;
            }
            return false;
        });
    }
}
