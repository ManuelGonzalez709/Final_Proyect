package com.example.anunciaya;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anunciaya.adapter.ImageAdapter;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class addAnuncio extends AppCompatActivity {
    private Spinner spinnerEstado,spinnerDivisa;
    private AutoCompleteTextView ubicacion;
    private ServerComunication comunication;
    private RecyclerView recyclerView;
    private ArrayList<String>fotos = new ArrayList<>();
    private ImageAdapter adapter;
    private Metodos metodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anuncio);
        comunication = new ServerComunication();
        metodos = new Metodos();
        String[]divisas  ={"€","$"};String []estados = {"Muy bueno","Usado","Nuevo"};
        spinnerDivisa = findViewById(R.id.newDivisa);setSpinner(spinnerDivisa,divisas);
        spinnerEstado = findViewById(R.id.newEstado);setSpinner(spinnerEstado,estados);
        ubicacion = findViewById(R.id.newUbicacion);autocompleterUbicacion();
        recyclerView = findViewById(R.id.recycler);
        adapter = new ImageAdapter(addAnuncio.this, fotos);

        findViewById(R.id.btremoveFoto).setOnClickListener(view->borrarUltFoto());
        findViewById(R.id.btaddFoto)
                .setOnClickListener(view ->ImagePicker.with(addAnuncio.this).crop().compress(1024).maxResultSize(120,120).start());
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {
                startActivity(new Intent(addAnuncio.this, ImageView.class).putExtra("image", path),
                ActivityOptions.makeSceneTransitionAnimation(addAnuncio.this, imageView, "image")
                .toBundle());
            }
        });
    }
    /*Metodo que se encarga de controlar cuando se ha cerrado el ImagePicker*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String urlFoto = data.getData().getPath();
        String urlServerFoto = metodos.subirFotoServer(urlFoto);
        fotos.add(urlServerFoto);
        recyclerView.setAdapter(adapter);
    }
    /*Metodo que borra la ultima foto*/
    private void borrarUltFoto(){
        if(fotos.size()>0){
            fotos.remove(fotos.size()-1);
            recyclerView.setAdapter(adapter);
        }else Toast.makeText(getApplicationContext(), "No hay fotos disponibles para borrar", Toast.LENGTH_SHORT).show();
    }
    /*Metodo que se encarga de cargar el buscador de */
    private void autocompleterUbicacion(){
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(addAnuncio.this, android.R.layout.simple_dropdown_item_1line, Municipios);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ubicacion.setAdapter(adapter);
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }
    private void setSpinner(Spinner spinner,String[]estados){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(addAnuncio.this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}