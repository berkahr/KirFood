package com.example.kirfood;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu);
    }
    MenuFragment menuFragment = new MenuFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    CartFragment cartFragment = new CartFragment();
    PromoFragment promoFragment = new PromoFragment();
    UserFragment userFragment = new UserFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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
        return false;
    }
}
