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

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;


public class MainActivity extends AppCompatActivity {

    SmoothBottomBar binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = findViewById(R.id.bottomBar);
        Config.checkApp(MainActivity.this);
        replaceFragment(new VillaFragment());
        binding.setBackground(null);
        binding.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int item) {
                if (item == 1) {
                    replaceFragment(new ProfileFragment());
                } else if (item == 0) {
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