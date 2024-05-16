package com.example.anunciaya.tools;

import android.content.SharedPreferences;

public class BundleRecoverry {

    private final SharedPreferences preferencias;
    public BundleRecoverry(SharedPreferences preferencias){this.preferencias = preferencias;}
    public void guardarString(String clave, String valor){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(clave, valor);
        editor.apply();
    }
    public void guardarInt(String clave, int valor){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(clave,valor);
        editor.apply();
    }
    public int recuperarInt(String clave){
        return preferencias.getInt(clave,-1);
    }
    public String recuperarString(String clave){
        return preferencias.getString(clave, null);
    }
}
