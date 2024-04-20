package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.ServerAsyncTask;
import com.example.anunciaya.tools.ServerComunication;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

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
                Log.i("ResultadoServer",resultadoServer);
            }

        }});

    }

    private Boolean LanzarPeticion(String clase , String metodo , String[]parametros){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                resultadoServer = ServerComunication.comunicacion(urlServer,clase,metodo,parametros);
            }
        });
        try{
            thread.start();
            thread.join();
            return true;}
        catch (Exception e){
            return false;
        }
    }
    private void abrirRegister(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    private int obtenerId(){
        try{
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getInt("data");
        }catch (Exception e){return -1;}
    }
    private boolean parsearLoggin(){
        try{
            JSONObject jsonObject = new JSONObject(resultadoServer);
            return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }

}
