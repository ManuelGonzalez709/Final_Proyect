package com.example.anunciaya;
/**
 * @Description Esto es una clase usada para registrar los datos de un nuevo usuario
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anunciaya.tools.BundleRecoverry;
import com.example.anunciaya.tools.Metodos;
import com.example.anunciaya.tools.Usuario;

import java.util.Calendar;
/*Esta es la clase principal*/
public class Register extends AppCompatActivity {
    /*Estos son los atributos de la clase*/
    private TextView tvTitReg;
    private TextView tvTitActDat;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button registerButton;
    private EditText nombre, apellidos,nomb_usu,email,telf,contras;
    private BundleRecoverry dataRecovery;
    private TextView iniciaSesion;
    private Boolean lanzadaMain = false;
    private Usuario user ;
    private ImageView imagen;

    /**
     * Este es el primer metodo que se ejecuta al abrir la actividad
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents(); // se encarga de cargar los atributos de la clase
        initDatePicker(); // se encarga de cargar el datePicker o el seleccionador de Fecha

        lanzadaMain = getIntent().getBooleanExtra("fromMainActivity", false);
        if (lanzadaMain) PrepararDatos(); // si la actividad ha sido lanzada desde el editor de usuario prepara los datos

        iniciaSesion.setOnClickListener(v -> LanzarLoggin());
        registerButton.setOnClickListener(v -> onclickInsertarUsuario());
    }

    /**
     * Metodo que se encarga de Inicializar los atributos de la clase
     */
    private void initComponents(){
        SharedPreferences sharedPreferences = getSharedPreferences("MisDatos", MODE_PRIVATE);
        dataRecovery = new BundleRecoverry(sharedPreferences);

        tvTitReg = findViewById(R.id.tvTitReg);
        tvTitReg.setVisibility(View.VISIBLE);
        tvTitActDat = findViewById(R.id.tvTitActDat);
        tvTitActDat.setVisibility(View.GONE);
        nombre = findViewById(R.id.registerNombre);
        apellidos = findViewById(R.id.registerApellidos);
        nomb_usu = findViewById(R.id.registerNombreUsuario);
        email = findViewById(R.id.registerEmail);
        telf = findViewById(R.id.registerTelefono);
        contras = findViewById(R.id.registerContraseña);
        iniciaSesion = findViewById(R.id.btRegistrateIniciaSesion);
        imagen = findViewById(R.id.imageView);

        dateButton = findViewById(R.id.registerEdad);
        dateButton.setText(getFecha());
        registerButton = findViewById(R.id.Registrar);

    }

    /**
     * Esto es el preparador de datos que se encarga de preparar los datos en caso de que se trate de un editor de usuarios
     */
    private void PrepararDatos(){
        Metodos metodos = new Metodos();
        try{
            String[]params = {Integer.toString(dataRecovery.recuperarInt("logginId"))};
            user = metodos.getUsuarioDataId(params);
            if(user != null){ // si el usuario no es nulo y tiene datos
                nombre.setText(user.getNombre());
                apellidos.setText(user.getApellidos());
                nomb_usu.setText(user.getNombUsu());
                dateButton.setText(user.getFechaNacimiento());
                email.setText(user.getEmail());
                telf.setText(user.getTelefono());
                registerButton.setText(R.string.guardCambios);
                imagen.setImageResource(R.drawable.user_logo_black);
                tvTitReg.setVisibility(View.GONE);
                tvTitActDat.setVisibility(View.VISIBLE);

                RelativeLayout preguntarCuenta = findViewById(R.id.tienesCuentaAsk);

                preguntarCuenta.setVisibility(View.GONE);

            }else{
                Toast.makeText(getApplicationContext(), "Error al Cargar los datos del Usuario", Toast.LENGTH_SHORT).show();
                finish(); // terrmina la actividad si no carga los datos del usuario
            }
        }catch (Exception e){
            Log.i("Error:",e.toString());}
    }

    /**
     * Metodo que se ejecuta al dar click en insertar usuario y se encarga de controlar los datos insertados y inserta este usuario
     */
    private void onclickInsertarUsuario(){
        Metodos metodos = new Metodos();
        if(nombre.getText().toString().compareTo("") != 0){
            if(apellidos.getText().toString().compareTo("")!= 0){
                if(nomb_usu.getText().toString().compareTo("")!= 0){
                    if(email.getText().toString().compareTo("")!= 0){
                        if(telf.getText().toString().compareTo("")!= 0){
                            if(contras.getText().toString().compareTo("")!= 0 || lanzadaMain){
                                if(dateButton.getText().toString().compareTo("")!=0){
                                    if(lanzadaMain){ //
                                        String[] params = {
                                                Integer.toString(dataRecovery.recuperarInt("logginId")), // id_usuario
                                                nombre.getText().toString(), // nombre
                                                apellidos.getText().toString(), // apellidos
                                                telf.getText().toString(), // telefono
                                                email.getText().toString(), // email
                                                dateButton.getText().toString(), // fecha_nac
                                                nomb_usu.getText().toString(), // nomb_usu
                                        };
                                        if(contras.getText().toString().compareTo("")!= 0){
                                            String[]contra = { Integer.toString(dataRecovery.recuperarInt("logginId")),contras.getText().toString()};
                                            if(!metodos.ActualizarContasena(contra))
                                                Toast.makeText(getApplicationContext(), "Error al Actualizar la contraseña", Toast.LENGTH_SHORT).show();
                                        }
                                        if(metodos.ActualizarUsuario(params))Toast.makeText(getApplicationContext(), "Usario Actualizado", Toast.LENGTH_SHORT).show();
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

    /**
     * Es un metodo que se encarga de lanzar la actividad de Loggin
     */
    private void LanzarLoggin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * Metodo que se encarga de lanzar el Main
     */
    private void LanzarMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * Metodo que se encarga de obtener la fecha del DatePicker para usarla en el metodo de insertar usuario
     * @return retorna la fecha ya parseada
     */
    private String getFecha() {
        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes+1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dia,mes,año);
    }

    /**
     * Metodo que se encarga de inicializar y preparar el DatePicker
     */
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, año, mes, dia) -> {
            mes = mes+1;
            String date = makeDateString(dia,mes,año);
            dateButton.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,año,mes,dia);
    }

    /**
     * Es un metodo usado por el DatePicker para mostrar el overview de la fecha
     * @param dia es el dia del mes
     * @param mes es el mes del año
     * @param año es el año
     * @return retrorna la fecha ya preparada
     */
    private String makeDateString(int dia, int mes, int año) {
        return año+"-"+mes+"-"+dia;

    }

    /**
     * Metodo que captura el click del datepicker
     * @param view contiene la vista del Register
     */
    public void openDatePicker(View view){
        datePickerDialog.show();
    }
}