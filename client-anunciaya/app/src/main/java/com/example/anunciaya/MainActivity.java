package com.example.anunciaya;
/**
 * @Description Esto es la clase main (principal)
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.3
 */
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
/*Esto es la clase principal*/
public class MainActivity extends AppCompatActivity {
    /*Estos son los atributos de la clase*/
    private BundleRecoverry dataRecovery;
    private int IdUser;

    /**
     * Este es el metodo principal de la clase que se encarga de lanzar el navigation
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        dataRecovery = new BundleRecoverry(sharedPreferences);
        IdUser = ComprobarLoggin();
        setupNavegacion();

    }

    /**
     * Este metodo se encarga de lanzar el navigation bar
     */
    private void setupNavegacion() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_hostfragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navHostFragment.getNavController());
    }

    /**
     * Este metodo se encarga de comprobar si la cuenta esta logged
     * @return retorna el Id del usuario
     */
    private int ComprobarLoggin(){
        return dataRecovery.recuperarInt("logginId");
    }
}