package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.adapter.ListAnuncios;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;

import java.util.List;

public class Envios extends AppCompatActivity {
    private TextView tvNoAnunVend;
    private RecyclerView recyclerView;
    private BundleRecoverry almacen;
    private int IdUser;
    private ListAdapter listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);

        initComponents();
        cargarAnuncios();
    }

    public void initComponents(){
        tvNoAnunVend = findViewById(R.id.tvNoAnunVend);
        recyclerView = findViewById(R.id.rvEnvios);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacen = new BundleRecoverry(sharedPreferences);
    }

    private void cargarAnuncios(){
        Metodos m = new Metodos();
        IdUser = almacen.recuperarInt("logginId"); // idUsuario logeado
        List<ListAnuncios> pedidos = m.getEnvios(new String[]{Integer.toString(IdUser)});
        if (pedidos != null) {
            listAdapter = new ListAdapter(pedidos, getApplicationContext(), null, R.layout.list_anuncios);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);
            if (listAdapter.getItemCount() > 0) {
                tvNoAnunVend.setVisibility(View.GONE);
            } else {
                tvNoAnunVend.setVisibility(View.VISIBLE);
            }
        }
    }
}