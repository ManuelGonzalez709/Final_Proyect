package com.example.anunciaya;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anunciaya.adapter.ImageAdapter;
import com.example.anunciaya.tools.Anuncio;
import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;
import com.github.dhaval2404.imagepicker.ImagePicker;

/**
 * @Description Esto es una clase creada para añadir anuncios o crear anuncios
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.3
 */
public class AddAnuncio extends AppCompatActivity {
    private Spinner spinnerEstado,spinnerCategoria;
    private AutoCompleteTextView ubicacion;
    private ServerComunication comunication;
    private RecyclerView recyclerView;
    private ArrayList<String>fotos = new ArrayList<>();
    private ImageAdapter adapter;
    private Metodos metodos;
    private Button boton;
    private EditText titulo,Descripicion,Precio;
    private BundleRecoverry almacenDatos;

    /**
     * Metodo principal on Create
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anuncio);
        comunication = new ServerComunication();
        metodos = new Metodos();
        String[]estados = {"Muy bueno","Usado","Nuevo"};
        spinnerCategoria = findViewById(R.id.newCategoria);setSpinner(spinnerCategoria,metodos.getCategorias());
        spinnerEstado = findViewById(R.id.newEstado);setSpinner(spinnerEstado,estados);
        ubicacion = findViewById(R.id.newUbicacion);autocompleterUbicacion();
        recyclerView = findViewById(R.id.recycler);
        titulo = findViewById(R.id.newTitulo);
        Descripicion = findViewById(R.id.newDescripcion);
        Precio = findViewById(R.id.newPrecio);


        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        almacenDatos = new BundleRecoverry(sharedPreferences);
        adapter = new ImageAdapter(AddAnuncio.this, fotos);

        findViewById(R.id.btremoveFoto).setOnClickListener(view->borrarUltFoto());
        findViewById(R.id.btaddFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fotos.size()<6) ImagePicker.with(AddAnuncio.this).crop().maxResultSize(480,320).start();
                else Toast.makeText(getApplicationContext(), "No se pueden añadir mas Fotos", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemClickListener((imageView, path) -> startActivity(new Intent(AddAnuncio.this, ImageView.class).putExtra("image", path),
                ActivityOptions.makeSceneTransitionAnimation(AddAnuncio.this, imageView, "image")
                        .toBundle()));
        boton = findViewById(R.id.btnewCrearAnuncio);
        boton.setOnClickListener(view -> CrearAnuncio());

    }

    /**
     * Metodo que se encarga de Crear el anuncio cogiendo todos los datos necesarios y llamando a la clase metodos
     */
    private void CrearAnuncio(){
        try{
            String[]args = {spinnerCategoria.getSelectedItem().toString()};
            if(args[0].toLowerCase().compareTo("None")!=0){
                int idCategoria = metodos.getCategoriaId(args);
                int idUsuario = almacenDatos.recuperarInt("logginId");
                if(titulo.getText().toString().compareTo("")!= 0){
                    if(Descripicion.getText().toString().compareTo("")!=0){
                        if(Descripicion.getText().toString().length() > 495){
                            Toast.makeText(AddAnuncio.this, "La descripción no puede contener más de 495 caracteres", Toast.LENGTH_SHORT).show();
                        }
                            if(Precio.getText().toString().compareTo("")!= 0){
                                if(ubicacion.getText().toString().compareTo("")!= 0){
                                    if(fotos.size()>0){
                                        Anuncio anuncioCreado = new Anuncio();
                                        anuncioCreado.setTitulo(titulo.getText().toString());
                                        anuncioCreado.setDescripcion(Descripicion.getText().toString());
                                        anuncioCreado.setPrecio(Precio.getText().toString());
                                        anuncioCreado.setUbicacion(ubicacion.getText().toString());
                                        anuncioCreado.setidCategoria(idCategoria);
                                        anuncioCreado.setEstado(spinnerEstado.getSelectedItem().toString());
                                        anuncioCreado.setFotos("");anuncioCreado.setIdUsuario(idUsuario);
                                        String [] parametros = {
                                                Integer.toString(idUsuario),Integer.toString(idCategoria),
                                                anuncioCreado.getTitulo(),anuncioCreado.getDescripcion(),
                                                anuncioCreado.getEstado(),anuncioCreado.getUbicacion(),
                                                anuncioCreado.getPrecio(),anuncioCreado.getFotos()};
                                        if(metodos.insertarAnuncio(parametros)){
                                            String[]paramsNuevos = {Integer.toString(idUsuario)};
                                            int idAnuncioCreado = metodos.getIdNewAnuncioUsuario(paramsNuevos);
                                            String fotosSubidas = "";
                                            for(int i = 0;i<fotos.size();i++){
                                                fotosSubidas+=metodos.subirFotoServer(fotos.get(i),idUsuario,idAnuncioCreado);
                                                if(i-1<fotos.size())fotosSubidas+=";";
                                            }
                                            String[]argumentosUploadImagen = {fotosSubidas,Integer.toString(idAnuncioCreado)};
                                            metodos.updateAnuncioUploadImagen(argumentosUploadImagen);
                                            Toast.makeText(getApplicationContext(),"Anuncio Creado Correctamente",Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else Toast.makeText(getApplicationContext(), "No se pudo insertar el anuncio", Toast.LENGTH_SHORT).show();
                                    }else Toast.makeText(getApplicationContext(), "Al menos añade una Foto", Toast.LENGTH_SHORT).show();
                                }else Toast.makeText(getApplicationContext(), "Introduzca la Ubicacion", Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(getApplicationContext(), "Introduzca el Precio", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getApplicationContext(), "Introducza la Descripcion", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Introduzca el Titulo del Anuncio", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getApplicationContext(), "Elija una Categoria valida", Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(getApplicationContext(), "Error: "+e.toString(), Toast.LENGTH_SHORT).show();}
    }

    /**
     * Metodo que se encarga de capturar cuando se cierra el cargador de fotos
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
     * Metodo que borra la ultima foto
     */
    private void borrarUltFoto(){
        if(fotos.size()>0){
            fotos.remove(fotos.size()-1);
            recyclerView.setAdapter(adapter);
        }else Toast.makeText(getApplicationContext(), "No hay fotos disponibles para borrar", Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que se encarga de Autocompletar la ubicacion
     */
    private void autocompleterUbicacion(){
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAnuncio.this, R.layout.auto_municipios_rojo2, Municipios);
            adapter.setDropDownViewResource(R.layout.auto_municipios_rojo2);
            ubicacion.setAdapter(adapter);
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

    /**
     * Esto es un metodo que se encarga de establecer los datos de los spinner
     * @param spinner se le pasa el spinner
     * @param estados se le pasa los datos que queremos cargar en los datos
     */
    public static void setSpinner(Spinner spinner,String[]estados){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), R.layout.spinner_categorias_rojo2, estados);
        adapter.setDropDownViewResource(R.layout.spinner_categorias_rojo2);
        spinner.setAdapter(adapter);
    }
    /**
     * Esto es un metodo que se encarga de establecer los datos de los spinner
     * @param spinner se le pasa el spinner
     * @param estados se le pasa los datos que queremos cargar en los datos
     */
    public static  void setSpinner(Spinner spinner,ArrayList<String>estados){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), R.layout.spinner_categorias_rojo2, estados);
        adapter.setDropDownViewResource(R.layout.spinner_categorias_rojo2);
        // Asignar el adaptador al Spinner
        spinner.setAdapter(adapter);
    }
}