package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.Usuario;

import java.util.Calendar;

public class Register extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button registerButton;
    private EditText nombre, apellidos,nomb_usu,email,telf,contras;
    private BundleRecoverry dataRecovery;
    private TextView iniciaSesion ;
    private Metodos metodos;
    private Boolean lanzadaMain = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDatePicker();
        metodos = new Metodos();
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        dataRecovery = new BundleRecoverry(sharedPreferences);

        nombre = findViewById(R.id.registerNombre);
        apellidos = findViewById(R.id.registerApellidos);
        nomb_usu = findViewById(R.id.registerNombreUsuario);
        email = findViewById(R.id.registerEmail);
        telf = findViewById(R.id.registerTelefono);
        contras = findViewById(R.id.registerContraseña);
        iniciaSesion = findViewById(R.id.btRegistrateIniciaSesion);


        dateButton = findViewById(R.id.registerEdad);
        dateButton.setText(getFecha());
        registerButton = findViewById(R.id.Registrar);

        lanzadaMain = getIntent().getBooleanExtra("fromMainActivity", false);
        if (lanzadaMain) PrepararDatos();

        iniciaSesion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                LanzarLoggin();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {onclickInsertarUsuario();}
        });
    }
    private void PrepararDatos(){
        try{
            String[]params = {Integer.toString(dataRecovery.recuperarInt("logginId"))};
            Usuario user = metodos.getUsuarioDataId(params);
            if(user != null){
                nombre.setText(user.getNombre());
                apellidos.setText(user.getApellidos());
                nomb_usu.setText(user.getNombUsu());
                dateButton.setText(user.getFechaNacimiento());
                email.setText(user.getEmail());
                telf.setText(user.getTelefono());
                registerButton.setText("Guardar Cambios");

                RelativeLayout preguntarCuenta = findViewById(R.id.tienesCuentaAsk);
                CardView contraseñaCard = findViewById(R.id.contraseñaCard);
                TextView textContraseña = findViewById(R.id.textoContraseña);

                preguntarCuenta.setVisibility(View.GONE);
                textContraseña.setVisibility(View.GONE);
                contraseñaCard.setVisibility(View.GONE);

            }else{
                Toast.makeText(getApplicationContext(), "Error al Cargar los datos del Usuario", Toast.LENGTH_SHORT).show();
                finish();
            }
        }catch (Exception e){
            Log.i("Error:",e.toString());}
    }

    private void onclickInsertarUsuario(){
        if(nombre.getText().toString().compareTo("") != 0){
            if(apellidos.getText().toString().compareTo("")!= 0){
                if(nomb_usu.getText().toString().compareTo("")!= 0){
                    if(email.getText().toString().compareTo("")!= 0){
                        if(telf.getText().toString().compareTo("")!= 0){
                            if(contras.getText().toString().compareTo("")!= 0 || lanzadaMain){
                                if(dateButton.getText().toString().compareTo("")!=0){
                                    if(lanzadaMain){
                                        String[] params = {
                                                Integer.toString(dataRecovery.recuperarInt("logginId")), // id_usuario
                                                nombre.getText().toString(), // nombre
                                                apellidos.getText().toString(), // apellidos
                                                telf.getText().toString(), // telefono
                                                email.getText().toString(), // email
                                                dateButton.getText().toString(), // fecha_nac
                                                nomb_usu.getText().toString(), // nomb_usu
                                        };
                                        if(metodos.ActualizarUsuario(params))Toast.makeText(getApplicationContext(), "Usario Actualizado", Toast.LENGTH_SHORT).show();
                                        else Toast.makeText(getApplicationContext(), "Error al Actualizar", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        String [] p1 = {nombre.getText().toString(),apellidos.getText().toString(),nomb_usu.getText().toString(),contras.getText().toString(),dateButton.getText().toString(),email.getText().toString(),telf.getText().toString()};
                                        int idUser = metodos.insertUsuario(p1);
                                        if(idUser!= -1){
                                            dataRecovery.guardarInt("logginId",idUser);LanzarMain();
                                        }else Toast.makeText(getApplicationContext(), "Usario Existente", Toast.LENGTH_SHORT).show();
                                    }
                                }else Toast.makeText(getApplicationContext(), "La fecha de nacimiento no puede estar vacia", Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(getApplicationContext(), "La contraseña no puede estar vacia", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getApplicationContext(), "El telefono no puede estar vacio", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getApplicationContext(), "El email no puede estar vacio", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "El nombre de usuario no puede estar vacio", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getApplicationContext(), "Los apellidos no pueden estar vacios", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(getApplicationContext(), "El nombre no puede estar vacio", Toast.LENGTH_SHORT).show();
    }
    private void LanzarLoggin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }
    private void LanzarMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    private String getFecha() {
        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes+1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dia,mes,año);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int año, int mes, int dia) {
                mes = mes+1;
                String date = makeDateString(dia,mes,año);
                dateButton.setText(date);
            }

        };
        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,año,mes,dia);
    }
    private String makeDateString(int dia, int mes, int año) {
        return año+ "/"+mes+"/"+dia;

    }
    public void openDatePicker(View view){
        datePickerDialog.show();
    }
}