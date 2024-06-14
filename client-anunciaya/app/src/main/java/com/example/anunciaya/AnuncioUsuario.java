package com.example.anunciaya;
/**
 * @Description Esto es una clase usada para Mostrar los Anuncios del Ususario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.3
 */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.anunciaya.tools.Metodos;

import java.util.ArrayList;
/*Esta es la clase Principal*/
public class AnuncioUsuario extends AppCompatActivity {
    /*Estos son los atributos de la clase*/
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
    private String fotos;

    /**
     * Esto es el metodo que se ejecuta al abrir dicha activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncio_usuario);
        initComponents();
        addDatosAnuncio();

        /*
         * Evento que se ejecuta cuando pulsamos en el botón de eliminar anuncio.
         * Si el anuncio se ha comprado no se puede borrar, si no se elimina correctamente.
         */
        btBorrarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos m = new Metodos();
                AlertDialog.Builder builder = new AlertDialog.Builder(AnuncioUsuario.this);

                builder.setMessage(R.string.dialogElimAnuncio)
                        .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(m.deleteAnuncio(new String[]{idAnuncio}).equals("true")){
                                    Toast.makeText(AnuncioUsuario.this, "¡El anuncio ha sido eliminado correctamente!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (m.deleteAnuncio(new String[]{idAnuncio}).equals("\"err_const\"")) {
                                    Toast.makeText(AnuncioUsuario.this, "No se puede eliminar un anuncio que ha sido comprado", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(AnuncioUsuario.this, "Ha ocurrido un error al intentar eliminar el anuncio", Toast.LENGTH_SHORT).show();
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
        /*Esto lo que hace es capturar cuando hemos hecho click en editar Anuncio*/
        btEditarAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnuncioUsuario.this, EditarAnuncio.class);
                intent.putExtra("au_idAnuncio", idAnuncio);
                intent.putExtra("au_titulo", tvTitulo.getText());
                intent.putExtra("au_descripcion", tvDescripcion.getText());
                intent.putExtra("au_precio", tvPrecio.getText());
                intent.putExtra("au_estado",tvEstado.getText());
                intent.putExtra("au_categoria", tvCategoria.getText());
                intent.putExtra("au_ubicacion", tvUbicacion.getText());
                intent.putExtra("au_fotos", fotos);
                startActivity(intent);
                finish();
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
        fotos = intent.getStringExtra("a_fotos");

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