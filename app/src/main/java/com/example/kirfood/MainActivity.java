package com.example.kirfood;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button morphButton, registerButton;
    private ImageButton closeLogin, closeRegister;
    private LinearLayout formLayout, formRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        morphButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.signUp);
        formLayout = findViewById(R.id.formLogin);
        formRegister = findViewById(R.id.formRegister);
        closeLogin = findViewById(R.id.closeFormLogin);
        closeRegister = findViewById(R.id.closeFormRegister);
        morphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morphAnimation();
            }
        });
        closeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAnimation();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAnimation();
            }
        });
        closeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeRegisterAnimation();
            }
        });
        }

    private void closeRegisterAnimation() {
        //RegisterAnimation
        ObjectAnimator formAnimatorRegister = ObjectAnimator.ofFloat(formRegister, "alpha", 1f, 0f);
        formAnimatorRegister.setDuration(500);
        formAnimatorRegister.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator buttonAnimatorRegister = ObjectAnimator.ofFloat(registerButton, "alpha", 0f, 1f);
        buttonAnimatorRegister.setDuration(500);
        buttonAnimatorRegister.setInterpolator(new AccelerateDecelerateInterpolator());
        formAnimatorRegister.start();
        buttonAnimatorRegister.start();
        formRegister.setVisibility(View.GONE);
        registerButton.setVisibility(View.VISIBLE);
    }

    private void registerAnimation() {
        //registerAnimation
        ObjectAnimator buttonAnimatorRegister = ObjectAnimator.ofFloat(registerButton, "alpha", 1f, 0f);
        buttonAnimatorRegister.setDuration(500);
        buttonAnimatorRegister.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator formAnimatorRegister = ObjectAnimator.ofFloat(formRegister, "alpha", 0f, 1f);
        formAnimatorRegister.setDuration(500);
        formAnimatorRegister.setInterpolator(new AccelerateDecelerateInterpolator());
        buttonAnimatorRegister.start();
        formAnimatorRegister.start();
        registerButton.setVisibility(View.GONE);
        formRegister.setVisibility(View.VISIBLE);
    }

    private void closeAnimation() {
        //LoginAnimation
        ObjectAnimator formAnimator = ObjectAnimator.ofFloat(formLayout, "alpha", 1f, 0f);
        formAnimator.setDuration(500);
        formAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator SignAnimator = ObjectAnimator.ofFloat(morphButton, "alpha", 0f, 1f);
        SignAnimator.setDuration(500);
        SignAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        formAnimator.start();
        SignAnimator.start();
        formLayout.setVisibility(View.GONE);
        morphButton.setVisibility(View.VISIBLE);
    }

    private void morphAnimation() {
        //loginAnimation
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(morphButton, "alpha", 1f, 0f);
        buttonAnimator.setDuration(500);
        buttonAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator formAnimator = ObjectAnimator.ofFloat(formLayout, "alpha", 0f, 1f);
        formAnimator.setDuration(500);
        formAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        buttonAnimator.start();
        formAnimator.start();
        morphButton.setVisibility(View.GONE);
        formLayout.setVisibility(View.VISIBLE);
    }
}