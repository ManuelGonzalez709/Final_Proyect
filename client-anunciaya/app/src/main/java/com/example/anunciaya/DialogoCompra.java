package com.example.anunciaya;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.content.Intent;
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

public class DialogoCompra extends DialogFragment {
    private EditText etDireccionCompra;
    private AutoCompleteTextView actvCiudadCompra;
    private EditText etCPCompra;
    private TextView tvTituloCompra;
    private TextView tvEstadoCompra;
    private TextView tvPrecioCompra;
    private Button btComprarComp;
    private String idAnuncio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_comprar, container, false);
        initComponents(view);
        addDatosCompra();
        autocompletarUbicacion();

        btComprarComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

    return view;
    }

    private void initComponents(View view){
        etDireccionCompra = view.findViewById(R.id.etDireccionCompra);
        actvCiudadCompra = view.findViewById(R.id.actvCiudadCompra);
        etCPCompra = view.findViewById(R.id.etCPCompra);
        tvTituloCompra = view.findViewById(R.id.tvTituloCompra);
        tvEstadoCompra = view.findViewById(R.id.tvEstadoCompra);
        tvPrecioCompra = view.findViewById(R.id.tvPrecioCompra);
        btComprarComp = view.findViewById(R.id.btComprarComp);
    }

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

    private void autocompletarUbicacion(){
        ServerComunication comunication = new ServerComunication();
        try{
            String[]Municipios = comunication.getMunicipios().split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity().getApplicationContext(), R.layout.auto_municipios, Municipios);
            adapter.setDropDownViewResource(R.layout.auto_municipios);
            // android.R.layout.simple_spinner_dropdown_item
            actvCiudadCompra.setAdapter(adapter);
        }catch (Exception e){
            Log.i("Error_Ubicacion",e.toString());
        }
    }


}

