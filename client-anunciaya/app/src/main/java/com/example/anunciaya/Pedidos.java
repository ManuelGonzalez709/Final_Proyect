package com.example.anunciaya;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anunciaya.adapter.ListAdapter;
import com.example.anunciaya.adapter.ListAnuncios;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;

import java.util.List;

public class Pedidos extends AppCompatActivity {
    private TextView tvNoAnunComprad;
    private BundleRecoverry almacen;
    private int IdUser;
    private ListAdapter listAdapter ;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        initComponents();
        cargarAnuncios();
    }

    private void initComponents(){
        recyclerView = findViewById(R.id.rvPedidos);
        tvNoAnunComprad = findViewById(R.id.tvNoAnunComprad);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacen = new BundleRecoverry(sharedPreferences);
    }

    private void cargarAnuncios(){
        Metodos m = new Metodos();
        IdUser = almacen.recuperarInt("logginId"); // idUsuario logeado
        List<ListAnuncios> pedidos = m.getPedidos(new String[]{Integer.toString(IdUser)});
        if (pedidos != null) {
            listAdapter = new ListAdapter(pedidos, getApplicationContext(), null, R.layout.list_pedidos);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);
            if (listAdapter.getItemCount() > 0) {
                tvNoAnunComprad.setVisibility(View.GONE);
            } else {
                tvNoAnunComprad.setVisibility(View.VISIBLE);
            }
        }
    }

}