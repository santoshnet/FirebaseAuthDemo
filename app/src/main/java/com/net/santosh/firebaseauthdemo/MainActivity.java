package com.net.santosh.firebaseauthdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
    }

    public void loginClicked(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void registerClicked(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
