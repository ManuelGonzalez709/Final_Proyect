package com.example.anunciaya;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anunciaya.adapter.ImageAdapter;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * @Description Esto es una clase que se encarga de Editar Anuncios ya creados
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
public class EditarAnuncio extends AppCompatActivity {
    /*Estos son los Atributos de la clase*/
    private EditText etTitutloMod;
    private EditText etDescripMod;
    private EditText etPrecioMod;
    private Spinner spEstadoMod;
    private Spinner spCategoriaMod;
    private Button btModAnuncio;
    private AutoCompleteTextView actvUbiMod;
    private String idAnuncio;
    ArrayList<String> estados;
    private ImageAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<String>fotos = new ArrayList<>();
    private BundleRecoverry almacenDatos;

    /**
     * Esto es el primer metodo que se ejecuta al abrirse la clase
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anuncio);
        initComponents();
        addDatosFormularioAnuncio();
        autocompletarUbicacion();

        findViewById(R.id.btremoveFoto).setOnClickListener(view->borrarUltFoto());
        findViewById(R.id.btaddFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fotos.size()<6) ImagePicker.with(EditarAnuncio.this).crop().maxResultSize(480,320).start();
                else Toast.makeText(getApplicationContext(), "No se pueden añadir mas Fotos", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {
                startActivity(new Intent(EditarAnuncio.this, ImageView.class).putExtra("image", path),
                        ActivityOptions.makeSceneTransitionAnimation(EditarAnuncio.this, imageView, "image")
                                .toBundle());
            }
        });

        btModAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos m = new Metodos();
                ArrayList<String> Salvadas = new ArrayList<>();
                String fotosSubidas = "";

                for (int i = 0; i < fotos.size(); i++) {
                    if (fotos.get(i).toString().contains("http://") || fotos.get(i).toString().contains("https://")) {
                        String[] fotoSplitted = fotos.get(i).split("/");
                        Salvadas.add(fotoSplitted[fotoSplitted.length - 1].toString());
                    }
                }

                if (m.borrarFotosIdAnuncio(idAnuncio, Salvadas)) { // Si Fotos Anuncio Borradas
                    for (int i = 0; i < fotos.size(); i++) {
                        if (!fotos.get(i).contains("http://") && !fotos.get(i).contains("https://")) {
                            fotosSubidas += m.subirFotoServer(fotos.get(i), almacenDatos.recuperarInt("logginId"), Integer.parseInt(idAnuncio));
                        } else {
                            fotosSubidas += fotos.get(i);
                        }
                        if (i < fotos.size() - 1) {
                            fotosSubidas += ";";
                        }
                    }
                }

                if (etDescripMod.getText().toString().length() > 495) {
                    Toast.makeText(EditarAnuncio.this, "La descripción no puede contener más de 495 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] params2 = {spCategoriaMod.getSelectedItem().toString()};
                int idcategoria = m.getCategoriaId(params2);
                String[] params = {idAnuncio,
                        etTitutloMod.getText().toString(),
                        etDescripMod.getText().toString(),
                        spEstadoMod.getSelectedItem().toString(),
                        actvUbiMod.getText().toString(),
                        etPrecioMod.getText().toString(),
                        fotosSubidas, Integer.toString(idcategoria)};

                if (m.updateAnuncio(params)) {
                    Toast.makeText(EditarAnuncio.this, "¡Anuncio actualizado con éxito!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditarAnuncio.this, "No se modificó ningún campo", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

    }

    /**
     * Este es el metodo que se encarga de capturar cuando se cierra el cargador de fotos
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!= null){
            String urlFoto = data.getData().getPath();
            fotos.add(urlFoto);
            recyclerView.setAdapter(adapter);
        }
    }

    /**
     * Método que asigna los datos del anuncio al formulario
     * a partir del intent de la activity anterior
     */
    private void addDatosFormularioAnuncio(){
        Metodos m = new Metodos();
        Intent intent = getIntent();
        estados = new ArrayList<>();
        estados.add("Muy bueno");
        estados.add("Usado");
        estados.add("Nuevo");


        idAnuncio = intent.getStringExtra("au_idAnuncio");
        etTitutloMod.setText(intent.getStringExtra("au_titulo"));
        etDescripMod.setText(intent.getStringExtra("au_descripcion"));
        etPrecioMod.setText(intent.getStringExtra("au_precio"));
        setSpinner(spCategoriaMod, m.getCategorias());
        spCategoriaMod.setSelection(m.getCategorias().indexOf(intent.getStringExtra("au_categoria")));
        setSpinner(spEstadoMod, estados);
        spEstadoMod.setSelection(estados.indexOf(intent.getStringExtra("au_estado")));
        actvUbiMod.setText(intent.getStringExtra("au_ubicacion"));
        String []fotosArray = intent.getStringExtra("au_fotos").split(";");
        for(int i = 0; i<fotosArray.length;i++)fotos.add(fotosArray[i]);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Metodo que se encarga de establecer los datos de un spinner pasado el espinner y los datos que queremos que almacene este
     * @param spinner es el spinner que queremos que se autocomplete con los datos
     * @param estados estos son los datos que pasamos para que se llene el spinner de estos datos
     */
    public static  void setSpinner(Spinner spinner,ArrayList<String>estados){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asignar el adaptador al Spinner
        spinner.setAdapter(adapter);
    }

    /**
     * Método que inicializa los componentes de la activity
     */
    private void initComponents(){
        etTitutloMod = findViewById(R.id.etTitutloMod);
        etDescripMod = findViewById(R.id.etDescripMod);
        etPrecioMod = findViewById(R.id.etPrecioMod);
        spEstadoMod = findViewById(R.id.spEstadoMod);
        spCategoriaMod = findViewById(R.id.spCategoriaMod);
        actvUbiMod = findViewById(R.id.actvUbiMod);
        btModAnuncio = findViewById(R.id.btModAnuncio);

        recyclerView = findViewById(R.id.recyclerEditar);
        adapter = new ImageAdapter(this, fotos);

        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacenDatos = new BundleRecoverry(sharedPreferences);

    }

    /**
     * Metodo que se encarga de Autocompletar la Ubicacion
     */
    private void autocompletarUbicacion(){
        ServerComunication comunication = new ServerComunication();
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditarAnuncio.this, android.R.layout.simple_dropdown_item_1line, Municipios);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            actvUbiMod.setAdapter(adapter);
        }catch (Exception e){
            Log.i("Error",e.toString());}
    }

    /**
     * Metodo que se encarga de borrar la ultima foto
     */
    private void borrarUltFoto(){
        if(fotos.size()>0){
            fotos.remove(fotos.size()-1);
            recyclerView.setAdapter(adapter);
        }else Toast.makeText(getApplicationContext(), "No hay fotos disponibles para borrar", Toast.LENGTH_SHORT).show();
    }
}