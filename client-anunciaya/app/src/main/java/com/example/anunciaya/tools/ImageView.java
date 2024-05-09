package com.example.anunciaya.tools;
/**
 * Clase ImageView
 * @Author Carlos Murillo Perez & Manuel Gonzalez Perez
 * @Version 1.0
 * @Description Es una clase usada en la Activity de Crear Anuncio , para el Carrousel
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.anunciaya.R;

public class ImageView extends AppCompatActivity {
    /**
     * Meotodo que se ecarga de Lanzar la Acitivy ImageView
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
    }
}