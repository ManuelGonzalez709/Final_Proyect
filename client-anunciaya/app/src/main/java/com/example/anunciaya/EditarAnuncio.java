package com.example.anunciaya;

import static com.example.anunciaya.addAnuncio.setSpinner;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;
import java.util.ArrayList;

public class EditarAnuncio extends AppCompatActivity {
    private EditText etTitutloMod;
    private EditText etDescripMod;
    private EditText etPrecioMod;
    private Spinner spEstadoMod;
    private Spinner spCategoriaMod;
    private Button btModAnuncio;
    private AutoCompleteTextView actvUbiMod;
    private String idAnuncio;
    ArrayList<String> estados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_anuncio);
        initComponents();
        addDatosFormularioAnuncio();
        autocompletarUbicacion();

        btModAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Metodos m = new Metodos();
                // ***********************************************
                // QUEDA POR ACTUALIZAR LAS FOTOS
                if(!etTitutloMod.getText().toString().isEmpty() && !etDescripMod.getText().toString().isEmpty()
                && !etPrecioMod.getText().toString().isEmpty() && !actvUbiMod.getText().toString().isEmpty()
                && !spEstadoMod.getSelectedItem().toString().isEmpty() && !spCategoriaMod.getSelectedItem().toString().isEmpty()){
                    if( m.updateAnuncio(new String[]{idAnuncio, etTitutloMod.getText().toString(),
                            etDescripMod.getText().toString(), spEstadoMod.getSelectedItem().toString(),
                            etPrecioMod.getText().toString(), "", String.valueOf(m.getCategoriaId(new String[]{spCategoriaMod.getSelectedItem().toString()}))})){
                        Toast.makeText(EditarAnuncio.this, "¡Anuncio actualizado con éxito!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else{
                        Toast.makeText(EditarAnuncio.this, "Ha ocurido un error al actualizar el anuncio", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(EditarAnuncio.this, "Hay campos del formulario de actualización vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
    }

    private void autocompletarUbicacion(){
        ServerComunication comunication = new ServerComunication();
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditarAnuncio.this, android.R.layout.simple_dropdown_item_1line, Municipios);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            actvUbiMod.setAdapter(adapter);
        }catch (Exception e){Log.i("Error",e.toString());}
    }
}
