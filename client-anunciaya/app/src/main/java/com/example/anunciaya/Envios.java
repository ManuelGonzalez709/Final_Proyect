package com.example.anunciaya;
/**
 * @Description Esto es una clase usada para Mostrar los Anuncios del Ususario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.3
 */
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
/*Esto es la clase principal*/
public class Envios extends AppCompatActivity {
    /*Estos son los atributos de la clase*/
    private TextView tvNoAnunVend;
    private RecyclerView recyclerView;
    private BundleRecoverry almacen;
    private int IdUser;
    private ListAdapter listAdapter ;

    /**
     * Este es el primer metodo que se ejecuta al ejecutar la activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);

        initComponents(); // este metodo se encarga de inicializar los attributos de arriba
        cargarAnuncios(); // este metodo se encarga de cargar los anuncios
    }

    /**
     * Este metodo se encarga de cargar los atributos de la clase
     */
    public void initComponents(){
        tvNoAnunVend = findViewById(R.id.tvNoAnunVend);
        recyclerView = findViewById(R.id.rvEnvios);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacen = new BundleRecoverry(sharedPreferences);
    }

    /**
     * Este metdo se encarga de cargar los anuncios de la clase
     */
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