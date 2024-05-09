package com.example.anunciaya.tools;
/**
 * Clase BundleRecovery
 * @Author Carlos Murillo Perez & Manuel Gonzalez Perez
 * @Version 2.0
 */

import android.content.SharedPreferences;

public class BundleRecoverry {
    //Atributos de la clase

    private SharedPreferences preferencias;

    /**
     * Constructor de la clase
     * @param preferencias
     */
    public BundleRecoverry(SharedPreferences preferencias){this.preferencias = preferencias;}

    /**
     * Metodo que se encarga de guardar un dato de tipo String
     * @param clave clave del almacenaje
     * @param valor valor a guardar
     */
    public void guardarString(String clave, String valor){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(clave, valor);
        editor.apply();
    }

    /**
     * Metodo que se encarga de guardar un Dato de tipo Integer
     * @param clave
     * @param valor
     */
    public void guardarInt(String clave, int valor){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(clave,valor);
        editor.apply();
    }

    /**
     * Metodo que se encarga de retornar un valor Int guardado
     * @param clave clave del almacenaje del valor
     * @return retorna un valor int
     */
    public int recuperarInt(String clave){
        return preferencias.getInt(clave,-1);
    }

    /**
     * Metodo que se encarga de Recuperar un valor String guardado
     * @param clave clave de almacenaje del valor
     * @return retorna un String
     */
    public String recuperarString(String clave){
        return preferencias.getString(clave, null);
    }
}
