package com.moutimid.rentownerapp.activities.Authentication;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.moutimid.rentownerapp.MainActivity;
import com.moutimid.rentownerapp.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_splash);

        int splashInterval = 3000;
        ImageView imageView = findViewById(R.id.logo);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 2.8F),
                PropertyValuesHolder.ofFloat("scaleY", 2.8F)
        );
        objectAnimator.setDuration(2000);
        objectAnimator.start();
        new Handler().postDelayed(this::goToApp, splashInterval);
    }

    public void goToApp() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//        Intent mainIntent = new Intent(SplashActivity.this, HomePage.class);
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
        else
        {
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}