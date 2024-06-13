package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anunciaya.fragments.InicioFragment;
import com.example.anunciaya.tools.BundleRecoverry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BundleRecoverry dataRecovery;
    private int IdUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        dataRecovery = new BundleRecoverry(sharedPreferences);
        IdUser = ComprobarLoggin();
        setupNavegacion();

    }

    private void setupNavegacion() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_hostfragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.getNavController());
    }
    private int ComprobarLoggin(){
        return dataRecovery.recuperarInt("logginId");
    }
}