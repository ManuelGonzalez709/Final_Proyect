package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.ServerComunication;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.snackbar.Snackbar;

public class login extends AppCompatActivity{
    private TextView registerButton;
    private BundleRecoverry dataRecovery;
    private Metodos metodos;
    private Button IniciarSesion;
    private EditText loginNombreUsuario;
    private EditText loginContraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        metodos = new Metodos();
        dataRecovery = new BundleRecoverry(sharedPreferences);
        if(dataRecovery.recuperarInt("logginId")!= -1)LanzarMain();

        registerButton = findViewById(R.id.btLoginRegistrate);
        loginContraseña = findViewById(R.id.loginContraseña);
        loginNombreUsuario = findViewById(R.id.loginNoUsu);
        IniciarSesion = findViewById(R.id.IniciarSesion);

        registerButton.setOnClickListener(new View.OnClickListener() // Te lleva a la ventana de Inicio de Sesion
        {public void onClick(View v) {abrirRegister();}});// Te lleva a la ventana de Inicio de Sesion
        IniciarSesion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(loginNombreUsuario.getText().toString().compareTo("")!= 0 && loginContraseña.getText().toString().compareTo("")!= 0){
                    String[] p1 = {loginNombreUsuario.getText().toString(), loginContraseña.getText().toString()};
                    if(metodos.verificarAuthCliente(p1)){
                       String[]p2 = {loginNombreUsuario.getText().toString()};
                          int idUser = metodos.getIdUser(p2);
                          dataRecovery.guardarInt("logginId",idUser);
                          LanzarMain();
                    }else Toast.makeText(getApplicationContext(), "Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Los campos no pueden estar Vacísos", Toast.LENGTH_SHORT).show();
        }});
    }
    /*Metodo que lanza la actividad de Main y Finaliza la Actividad de Loggin*/
    private void LanzarMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    /*Metodo que se encarga de abrir la ventana de Registro*/
    private void abrirRegister(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
        finish();
    }
}
