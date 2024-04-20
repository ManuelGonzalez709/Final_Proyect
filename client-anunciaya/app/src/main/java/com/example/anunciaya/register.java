package com.example.anunciaya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class register extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDatePicker();
        dateButton = findViewById(R.id.registerEdad);
        dateButton.setText(getFecha());
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
        return mes+ "/"+dia+"/"+año;

    }
    public void openDatePicker(View view){
        datePickerDialog.show();
    }
}