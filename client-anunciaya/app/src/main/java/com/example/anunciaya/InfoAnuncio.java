package com.example.anunciaya;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoAnuncio extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvDescripcion;
    private TextView tvPrecio;
    private TextView tvUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);
        initComponents();
        addDatosAnuncio();
    }

    private void initComponents() {
        tvTitulo = findViewById(R.id.tvTituloArt);
        tvDescripcion = findViewById(R.id.tvDescripcionArt);
        tvPrecio = findViewById(R.id.tvPrecioArt);
        tvUbicacion = findViewById(R.id.tvUbicacionArt);
    }

    private void addDatosAnuncio() {
        Intent intent = getIntent();
        tvTitulo.setText(intent.getStringExtra("a_titulo"));
        tvDescripcion.setText(intent.getStringExtra("a_descripcion"));
        tvPrecio.setText(intent.getStringExtra("a_precio"));
        tvUbicacion.setText(intent.getStringExtra("a_ubicacion"));
    }

}
