package com.example.anunciaya;
/**
 * @Description Esto es una clase usada para Mostrar los Anuncios del Ususario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.3
 */
import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;
/*Clase principal*/
public class DialogoCompra extends DialogFragment {
    /*Atributos de la clase*/
    private EditText etDireccionCompra;
    private AutoCompleteTextView actvCiudadCompra;
    private EditText etCPCompra;
    private TextView tvTituloCompra;
    private TextView tvEstadoCompra;
    private TextView tvPrecioCompra;
    private Button btComprarComp;
    private String idAnuncio;

    /**
     * Esto es el primer metodo que se ejecuta al abrir la activity
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return retorna la vista
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_comprar, container, false);
        initComponents(view); // es un inicializador de variables
        addDatosCompra(); // se encarga de añadir los datos de compra
        autocompletarUbicacion(); // se encarga de cargar los datos de ubicacion

        btComprarComp.setOnClickListener(v -> {
            if(!etDireccionCompra.getText().toString().isEmpty() && !actvCiudadCompra.getText().toString().isEmpty()
                    && !etCPCompra.getText().toString().isEmpty()){
                // El cp no puede tener más de cinco nº
                if (etCPCompra.getText().length() > 5){
                    Toast.makeText(getContext(), "El código postal no puede tener más de cinco números", Toast.LENGTH_SHORT).show();
                } else{
                    Metodos m = new Metodos();
                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                    BundleRecoverry dataRecovery = new BundleRecoverry(sharedPreferences);
                    int idUsuario = dataRecovery.recuperarInt("logginId");

                    if(m.insertPedido(new String[]{String.valueOf(idUsuario), idAnuncio, etDireccionCompra.getText().toString(),
                            actvCiudadCompra.getText().toString(), etCPCompra.getText().toString() })){
                        Toast.makeText(getContext(), "¡Artículo comprado exitosamente!", Toast.LENGTH_SHORT).show();
                        dismiss(); // Cerrar ventana dialogo
                        getActivity().finish(); // Cerrar activity y volver al fragment inicio
                    } else{
                        Toast.makeText(getContext(), "Ha ocurrido un error al realizar la compra", Toast.LENGTH_SHORT).show();
                    }
                }
            } else{
                Toast.makeText(getContext(), "Por favor, completa todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            }
        });

    return view;
    }

    /**
     * Esto es un metodo que se encarga de inicializar los atributos predefinidos en la clase
     * @param view contiene la vista de la clase
     */
    private void initComponents(View view){
        etDireccionCompra = view.findViewById(R.id.etDireccionCompra);
        actvCiudadCompra = view.findViewById(R.id.actvCiudadCompra);
        etCPCompra = view.findViewById(R.id.etCPCompra);
        tvTituloCompra = view.findViewById(R.id.tvTituloCompra);
        tvEstadoCompra = view.findViewById(R.id.tvEstadoCompra);
        tvPrecioCompra = view.findViewById(R.id.tvPrecioCompra);
        btComprarComp = view.findViewById(R.id.btComprarComp);
    }

    /**
     * Esto es un metodo que se encarga de añadir los datos de compra
     */
    public void addDatosCompra(){
        Bundle args = getArguments();
        if (args != null) {
            String titulo = args.getString("ac_titulo");
            String estado = args.getString("ac_estado");
            String precio = args.getString("ac_precio");
            idAnuncio = args.getString("ac_idAnuncio");

            tvTituloCompra.setText(titulo);
            tvEstadoCompra.setText(estado);
            tvPrecioCompra.setText(precio);
        }
    }

    /**
     * Esto es un metodo que se encarga de Autocompletar la Ubicacion
     */
    private void autocompletarUbicacion(){
        ServerComunication comunication = new ServerComunication();
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity().getApplicationContext(), R.layout.auto_municipios_rojo3, Municipios);
            adapter.setDropDownViewResource(R.layout.auto_municipios_rojo3);
            // android.R.layout.simple_spinner_dropdown_item
            actvCiudadCompra.setAdapter(adapter);
        }catch (Exception e){
            Log.i("Error_Ubicacion",e.toString());
        }
    }


}

