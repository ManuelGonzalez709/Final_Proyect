package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anunciaya.tools.BundleRecoverry;

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

    }
    private int ComprobarLoggin(){
        return dataRecovery.recuperarInt("logginId");
    }
}