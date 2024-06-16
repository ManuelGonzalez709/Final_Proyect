package com.example.anunciaya.adapter;
/**
 * @Description Esto es una clase usada para el tema del carrousel
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 1.0
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.anunciaya.R;

public class ImageView extends AppCompatActivity {
    /**
     * Esto es el Constructor por defecto que trae la vista
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