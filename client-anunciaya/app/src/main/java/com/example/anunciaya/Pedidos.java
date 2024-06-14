package com.example.anunciaya;
/**
 * @Description Esto es una clase que se encarga de ver los pedidos del Usuario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.3
 */
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
/*Esta es la clase pricipal*/
public class Pedidos extends AppCompatActivity {
    /*Estos son los atributos de la clase*/
    private TextView tvNoAnunComprad;
    private BundleRecoverry almacen;
    private int IdUser;
    private ListAdapter listAdapter ;
    private RecyclerView recyclerView;

    /**
     * Esto es el primer metodo que se ejecuta al abrir la app
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        initComponents(); // se encarga de cargar los atrtibutos de la clase
        cargarAnuncios(); // se encarga de cargar los anuncios de la clase
    }

    /**
     * Es un metodo que se encarga de cargar los atributos de la clase
     */
    private void initComponents(){
        recyclerView = findViewById(R.id.rvPedidos);
        tvNoAnunComprad = findViewById(R.id.tvNoAnunComprad);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacen = new BundleRecoverry(sharedPreferences);
    }

    /**
     * Es un metodo que se encarga de cargar los anuncios que tenemos en pedidos
     */
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