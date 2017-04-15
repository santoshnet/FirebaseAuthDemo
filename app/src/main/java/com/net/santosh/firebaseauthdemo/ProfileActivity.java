package com.net.santosh.firebaseauthdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText name, address;
    DatabaseReference databaseReference;
    String na, add, email, uid;
    TextView _email, nam, add1;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        uid = firebaseAuth.getCurrentUser().getUid();
        Toast.makeText(getApplicationContext(), uid, Toast.LENGTH_LONG).show();

        email = firebaseAuth.getCurrentUser().getEmail();
        _email = (TextView) findViewById(R.id.user_email);
        nam = (TextView) findViewById(R.id.user_na);
        add1 = (TextView) findViewById(R.id.user_ad);
        name = (EditText) findViewById(R.id.user_name);
        address = (EditText) findViewById(R.id.user_address);
        _email.setText(email);

        updateUserProfile();




    }

    private void updateUserProfile() {
        databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserInformation userInformation = dataSnapshot.getValue(UserInformation.class);
                if (userInformation == null) {
                    Toast.makeText(getApplicationContext(),
                            "Error: could not fetch user.",
                            Toast.LENGTH_SHORT).show();

                } else {
                    nam.setText(userInformation.getName());
                    add1.setText(userInformation.getAddress());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onLogOutClicked(View view) {
        firebaseAuth.signOut();
        finish();
    }

    public void onsaveClicked(View view) {
        na = name.getText().toString().trim();
        add = address.getText().toString().trim();
        UserInformation userInformation = new UserInformation(na, add, email, uid);
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child("users").child(user.getUid()).setValue(userInformation);
        Toast.makeText(getApplicationContext(), "Saving......", Toast.LENGTH_LONG).show();
        updateUserProfile();
    }
}
