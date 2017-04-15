package com.net.santosh.firebaseauthdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText name, address;
    DatabaseReference databaseReference;
    String na, add;
    TextView _email, nam, add1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String email = firebaseAuth.getCurrentUser().getEmail();
        _email = (TextView) findViewById(R.id.user_email);
        nam = (TextView) findViewById(R.id.user_na);
        add1 = (TextView) findViewById(R.id.user_ad);
        name = (EditText) findViewById(R.id.user_name);
        address = (EditText) findViewById(R.id.user_address);
        _email.setText(email);

    }

    public void onLogOutClicked(View view) {
        firebaseAuth.signOut();
        finish();
    }

    public void onsaveClicked(View view) {
        na = name.getText().toString().trim();
        add = address.getText().toString().trim();
        UserInformation userInformation = new UserInformation(na, add);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(getApplicationContext(), "Saving......", Toast.LENGTH_LONG).show();
        nam.setText(databaseReference.child(user.getUid()).getKey());

        if (user != null) {
            nam.setText(user.getDisplayName());
            add1.setText(user.getUid());
        }

    }
}
