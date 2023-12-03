package com.example.kirfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    private TextView seeAll;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        seeAll = (TextView) findViewById(R.id.clickSeeAll);
//        bottomNavigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu);

//        seeAll
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
//    HomeFragment homeFragment = new HomeFragment();
//    MenuFragment menuFragment = new MenuFragment();
//    CartFragment cartFragment = new CartFragment();
//    PromoFragment promoFragment = new PromoFragment();
//    UserFragment userFragment = new UserFragment();


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        switch (item.getItemId()){
//            case R.id.menu:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.FragmentBottomNav, menuFragment)
//                        .commit();
//                return true;
//            case R.id.favorit:
//                getSupportFragmentManager()
//                            .beginTransaction()
//                        .replace(R.id.FragmentBottomNav, favoriteFragment)
//                        .commit();
//                return true;
//            case R.id.cart:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.FragmentBottomNav, cartFragment)
//                        .commit();
//                return true;
//            case R.id.promo:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.FragmentBottomNav, promoFragment)
//                        .commit();
//                return true;
//            case R.id.user:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.FragmentBottomNav, userFragment)
//                        .commit();
//                return true;
//        }
//        return false;
//    }
}
