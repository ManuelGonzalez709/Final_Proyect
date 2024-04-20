package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.ServerComunication;

import org.json.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class login extends AppCompatActivity{
    private TextView registerButton;
    private BundleRecoverry dataRecovery;
    private Button IniciarSesion;
    private EditText loginNombreUsuario;
    private EditText loginContraseña;
    private String urlServer = "http://192.168.18.5/sv-php/index.php";
    private String resultadoServer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        dataRecovery = new BundleRecoverry(sharedPreferences);
        //if(dataRecovery.recuperarInt("logginId")!= -1)LanzarMain();
        // esto de arriba si lo descomentas , al estar la sesion iniciada te lanza al main
        // pero como todabia no tenemos boton de desloguearse lo quito porque sino no me manda al main

        registerButton = findViewById(R.id.btLoginRegistrate);
        loginContraseña = findViewById(R.id.loginContraseña);
        loginNombreUsuario = findViewById(R.id.loginNoUsu);
        IniciarSesion = findViewById(R.id.IniciarSesion);

        registerButton.setOnClickListener(new View.OnClickListener() // Te lleva a la ventana de Inicio de Sesion
        {public void onClick(View v) {abrirRegister();}});
        // Te lleva a la ventana de Inicio de Sesion
        IniciarSesion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            String[] p1 = {loginNombreUsuario.getText().toString(), loginContraseña.getText().toString()};
            if(LanzarPeticion("Auth","verificarAuthClient",p1)){
                if(parsearLoggin(resultadoServer)){
                    String[]p2 = {loginNombreUsuario.getText().toString()};
                    if(LanzarPeticion("Usuario","getIdUser",p2)){
                        int idUser = obtenerId(resultadoServer);
                        dataRecovery.guardarInt("logginId",idUser);
                        LanzarMain();
                    }
                }
            }

        }});

    }
    private void LanzarMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private Boolean LanzarPeticion(String clase , String metodo , String[]parametros){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                resultadoServer = ServerComunication.comunicacion(urlServer,clase,metodo,parametros);
            }
        });
        try{
            thread.start();thread.join();return true;
        }catch (Exception e){
            return false;
        }
    }
    private void abrirRegister(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    private int obtenerId(String datos){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(datos);
            String dataString = jsonNode.get("data").asText();
            JsonNode dataNode = objectMapper.readTree(dataString);
            return dataNode.get("id").asInt();
        }catch (Exception e){return -1;}
    }
    private boolean parsearLoggin(String datos){
        try{
            JSONObject jsonObject = new JSONObject(datos);
            return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }

}
