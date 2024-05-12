package com.example.anunciaya;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.anunciaya.tools.Metodos;

import java.util.ArrayList;

public class AnunciosUsuario extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvDescripcion;
    private TextView tvPrecio;
    private TextView tvEstado;
    private TextView tvCategoria;
    private TextView tvUbicacion;
    private ImageSlider isFotosAnuncio;
    private Button btEditarAnuncio;
    private Button btBorrarAnuncio;
    private String idAnuncio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios_usuario);
        initComponents();
        addDatosAnuncio();

        btBorrarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos m = new Metodos();
                AlertDialog.Builder builder = new AlertDialog.Builder(AnunciosUsuario.this);

                builder.setMessage(R.string.dialogElimAnuncio)
                        .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(m.deleteAnuncio(new String[]{idAnuncio})){
                                    Toast.makeText(AnunciosUsuario.this, "¡El anuncio ha sido eliminado correctamente!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else{
                                    Toast.makeText(AnunciosUsuario.this, "El anuncio no se puede eliminar, ya que ha sido adquirido", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btEditarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnunciosUsuario.this, EditarAnuncio.class);
                startActivity(intent);

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
        isFotosAnuncio = findViewById(R.id.isFotosAnuncio);
        btEditarAnuncio = findViewById(R.id.btEditarAnuncio);
        btBorrarAnuncio = findViewById(R.id.btBorrarAnuncio);
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
