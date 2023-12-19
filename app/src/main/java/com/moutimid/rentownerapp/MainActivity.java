package com.moutimid.rentownerapp;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutimid.rentownerapp.Fragment.ProfileFragment;
import com.moutimid.rentownerapp.Fragment.VillaFragment;
import com.moutimid.rentownerapp.helper.Config;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = findViewById(R.id.bottomNavigationView);
        Config.checkApp(MainActivity.this);
        replaceFragment(new VillaFragment());
        binding.setBackground(null);
        binding.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.user) {
                    replaceFragment(new ProfileFragment());

                } else if (item.getItemId() == R.id.home) {
                    replaceFragment(new VillaFragment());
                }
                return true;
            }
        });

    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}