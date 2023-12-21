package com.moutimid.rentownerapp.activities.Authentication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.moutimid.rentownerapp.MainActivity;
import com.moutimid.rentownerapp.R;
import com.moutimid.rentownerapp.helper.Constants;
import com.moutimid.rentownerapp.model.UserModel;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    EditText email, password;

    String email_str, password_str;
    String name, emailstr, image_gmail;
    private static final int RC_SIGN_IN = 1;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference mDatabase;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initComponent();
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initValues();
                loginRequest();
            }
        });
    }

    public void initComponent() {
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void initValues() {
        email_str = email.getText().toString();
        password_str = password.getText().toString();
    }


    private void loginRequest() {
        if (email_str.length() == 0) {
            email.setError("Please Error");
        } else if (password_str.length() == 0) {
            password.setError("Please Error");
        } else {
            Dialog lodingbar = new Dialog(LoginActivity.this);
            lodingbar.setContentView(R.layout.loading);
            Objects.requireNonNull(lodingbar.getWindow()).setBackgroundDrawable(new ColorDrawable(UCharacter.JoiningType.TRANSPARENT));
            lodingbar.setCancelable(false);
            lodingbar.show();
            Constants.auth().signInWithEmailAndPassword(
                    email.getText().toString(),
                    password.getText().toString()
            ).addOnSuccessListener(authResult -> {
                Constants.OwnerReference.child(authResult.getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userNew = snapshot.getValue(UserModel.class);
                        Stash.put("userID", authResult.getUser().getUid());
                        Stash.put("userModel", userNew.getName());
                        lodingbar.dismiss();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finishAffinity();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }
}