package com.example.kirfood;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.kirfood.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button morphButton, registerButton, login, register;
    private LinearLayout formLayout, formRegister;
    private EditText userLogin, passLogin, userRegister, nameRegister, passRegister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        morphButton = (Button) findViewById(R.id.login);
        registerButton = (Button) findViewById(R.id.signUp);
        formLayout = (LinearLayout) findViewById(R.id.formLogin);
        formRegister = (LinearLayout) findViewById(R.id.formRegister);
        login = (Button) findViewById(R.id.buttonFormLogin);
        register = (Button) findViewById(R.id.buttonFormRegister);
        userLogin = (EditText) findViewById(R.id.usernameLogin);
        passLogin = (EditText) findViewById(R.id.passwordLogin);
        userRegister = (EditText) findViewById(R.id.usernameRegister);
        nameRegister = (EditText) findViewById(R.id.nameRegister);
        passRegister = (EditText) findViewById(R.id.passwordRegister);

        mAuth = FirebaseAuth.getInstance();
        //init database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = FirebaseDatabase.getInstance().getReference().child("user");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLogin.getText().length()>0 && passLogin.getText().length()>0){
                    Login(userLogin.getText().toString(), passLogin.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Please fill all the required data!", Toast.LENGTH_SHORT).show();
                }
//no Auth
//                table_user.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        //check user
//                        if(snapshot.child(userLogin.getText().toString()).exists()){
//                            mDialog.dismiss();
//                            //get data user
//                            User user = snapshot.child(userLogin.getText().toString()).getValue(User.class);
//                            if (user.getPassword().equals(passLogin.getText().toString())){
//                                Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(), HomeFragment.class));
//                            } else {
//                                Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                            }
//                        }else {
//                            mDialog.dismiss();
//                            Toast.makeText(MainActivity.this, "User Not Found 404!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userRegister.getText().length()>0 && nameRegister.getText().length()>0 && passRegister.getText().length()>0){
                    Register(userRegister.getText().toString(), nameRegister.getText().toString(), passRegister.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "Please fill all the required data!", Toast.LENGTH_SHORT).show();
                }
//                table_user.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.child(userRegister.getText().toString()).exists()){
//                            mDialog.dismiss();
//                            Toast.makeText(MainActivity.this, "This Username is already exist", Toast.LENGTH_SHORT).show();
//                        }else {
//                            mDialog.dismiss();
//                            User user = new User(nameRegister.getText().toString(),passRegister.getText().toString());
//                            table_user.child(userRegister.getText().toString()).setValue(user);
//                            Toast.makeText(MainActivity.this, "Register succesfully", Toast.LENGTH_SHORT).show();
//                            userRegister.getText().clear();
//                            nameRegister.getText().clear();
//                            passRegister.getText().clear();
//                            closeRegisterAnimation();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });
        //animasi Login, Register
        morphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morphAnimation();
                closeRegisterAnimation();
            }
        });
//        closeLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeAnimation();
//            }
//        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAnimation();
                closeAnimation();
            }
        });
//        closeRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closeRegisterAnimation();
//            }
//        });
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
//    Auth
    // Register
    private void Register(String userRegister, String nameRegister, String passRegister){
        ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please Wait...");
        mDialog.show();
        mAuth.createUserWithEmailAndPassword(userRegister, passRegister).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult()!=null){
                    mDialog.dismiss();
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if (firebaseUser!=null){
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nameRegister)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                closeRegisterAnimation();
                                                Toast.makeText(getApplicationContext(), "Register Success!", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getApplicationContext(), "Failed to send verification!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }else {
                                    Toast.makeText(getApplicationContext(), "Register Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "Register Failed!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //Login
    private void Login(String userLogin, String passLogin){
        mAuth.signInWithEmailAndPassword(userLogin, passLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful() && task.getResult()!=null){
                 if (mAuth.getCurrentUser().isEmailVerified()){
                     Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(getApplicationContext(), HomeFragment.class));
                 }else {
                     Toast.makeText(getApplicationContext(), "Please verify your email first!", Toast.LENGTH_SHORT).show();
                 }
             }else {
                 Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

        }
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}