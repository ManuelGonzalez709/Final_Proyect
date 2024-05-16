package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;

public class Login extends AppCompatActivity{
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
        // comprueba si has iniciado sesion
        // si logginId != -1 ha iniciado sesion
        //dataRecovery.guardarInt("logginId", -1);
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
                }else Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}
