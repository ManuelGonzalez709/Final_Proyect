package com.example.anunciaya.tools;
/**
 * @Description Esto es una clase que se encarga de mostrar la informacion del Anuncio
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.anunciaya.DialogoCompra;
import com.example.anunciaya.R;

import java.util.ArrayList;

public class InfoAnuncio extends AppCompatActivity {
    /*Contiene los Atributos de la clase*/
    private TextView tvTitulo;
    private TextView tvDescripcion;
    private TextView tvPrecio;
    private TextView tvNombCompletoUsuario;
    private TextView tvEstado;
    private TextView tvCategoria;
    private TextView tvUbicacion;
    private ImageSlider isFotosAnuncio;
    private Button btComprar;
    private String idAnuncio;

    /**
     * Esto es el primer metdoo que se ejecuta al abrir esta vista
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio);
        initComponents();
        addDatosAnuncio();

        /*
         * Evento que se produce al pulsar en el boton comprar para crear
         * un nuevo pedido del anuncio con el comprador que haya
         * iniciado sesion como usario
         */
        btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoCompra dialogo = new DialogoCompra();
                Bundle bundle = new Bundle();
                bundle.putString("ac_titulo", tvTitulo.getText().toString());
                bundle.putString("ac_estado", tvEstado.getText().toString());
                bundle.putString("ac_precio", tvPrecio.getText().toString());
                bundle.putString("ac_idAnuncio", String.valueOf(idAnuncio));
                dialogo.setArguments(bundle);
                dialogo.show(getSupportFragmentManager(), "DialogoCompra");
            }
        });
    }

        /**
         * Método que inicializa los componentes
         */
    private void initComponents() {
        tvTitulo = findViewById(R.id.tvTituloArt);
        tvDescripcion = findViewById(R.id.tvDescripcionArt);
        tvPrecio = findViewById(R.id.tvPrecioArt);
        tvEstado = findViewById(R.id.tvEstadoArt);
        tvCategoria = findViewById(R.id.tvCategoriaArt);
        tvUbicacion = findViewById(R.id.tvUbicacionArt);
        tvNombCompletoUsuario = findViewById(R.id.tvNombCompleto);
        isFotosAnuncio = findViewById(R.id.isFotosAnuncio);
        btComprar = findViewById(R.id.btComprar);
    }

    /**
     * Método que asigna los datos del anuncio y propietario del anuncio
     * a mostrar obteniendo los datos mediante un intent
     */
    private void addDatosAnuncio() {
        ArrayList<SlideModel> imageList = new ArrayList<>();

        Intent intent = getIntent();
        idAnuncio = intent.getStringExtra("idAnuncio");
        tvTitulo.setText(intent.getStringExtra("a_titulo"));
        tvDescripcion.setText(intent.getStringExtra("a_descripcion"));
        tvPrecio.setText(intent.getStringExtra("a_precio"));
        tvEstado.setText(intent.getStringExtra("a_estado"));
        tvCategoria.setText(intent.getStringExtra("a_descripCategoria"));
        tvUbicacion.setText(intent.getStringExtra("a_ubicacion"));
        tvNombCompletoUsuario.setText(intent.getStringExtra("a_nombCompUsu"));
        String fotos = intent.getStringExtra("a_fotos");

        // Separar la ruta de la foto usando ";" como separador
        String[] fotoSplit = fotos.split(";");

            // Agregar cada foto individualmente al ArrayList
        for (String rutaFoto : fotoSplit) {
            if (!rutaFoto.isEmpty()) {
                imageList.add(new SlideModel(rutaFoto, ScaleTypes.CENTER_CROP));
            }
        }
        // Asignar la lista de imágenes al ImageSlider
        isFotosAnuncio.setImageList(imageList);
    }
}
