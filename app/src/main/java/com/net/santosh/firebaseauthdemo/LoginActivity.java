package com.net.santosh.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString().trim();
                String _password = password.getText().toString().trim();
                if (TextUtils.isEmpty(_email)) {
                    email.setError("Please Enter Email ");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(_password)) {
                    password.setError("Please Enter Email ");
                    password.requestFocus();
                    return;
                }
                progressDialog.setMessage("Logging User...");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(_email, _password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Login Un Successfull. Please Try Again...", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });

            }
        });

    }

    public void onSignUpClicked(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
