package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;

public class Envios extends AppCompatActivity {
    private BundleRecoverry almacen;
    private int IdUser;
    private ListAdapter listAdapter ;
    private Metodos m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacen = new BundleRecoverry(sharedPreferences);
        m = new Metodos();
        CargarAnuncios();
    }
    private void CargarAnuncios(){
        RecyclerView recyclerView = findViewById(R.id.rvEnvios);
        IdUser = almacen.recuperarInt("logginId"); // idUsuario logeado
        listAdapter = new ListAdapter(m.getEnvios(new String[]{Integer.toString(IdUser)}), getApplicationContext(),null, R.layout.list_anuncios);
        if(listAdapter.getItemCount()>0){
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);
        }
    }
}