package com.example.anunciaya.tools;
/**
 * @Description Esto es una clase que se encarga de guardar datos en Caché del Movil
 * @Auhtor Carlos Murillo Perez & Manuel Gonzalez Perez
 * @version 2.0
 */
import android.content.SharedPreferences;
/*Esta es la clase Principal*/
public class BundleRecoverry {
    /*Estos son los Atributos de la Clase*/
    private final SharedPreferences preferencias;

    /**
     * Esto es el Cnstructor principal al que se le pasa el SharedPreferences
     * @param preferencias contiene el poder de recuperar informacion de Cache y el guardado de la misma
     */
    public BundleRecoverry(SharedPreferences preferencias){this.preferencias = preferencias;}

    /**
     * Esto es un Metodo que se encarga de Guardar Un string en Caché
     * @param clave contiene la clave por la que vamos a guardar el valor (String)
     * @param valor contiene el valor que va a guardar dicha clave
     */
    public void guardarString(String clave, String valor){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString(clave, valor);
        editor.apply();
    }

    /**
     * Esto es un metodo que se ecarga de guardar un Entero por clave - valor
     * @param clave contiene la clave por la que vamos a guardar un dato tipo Int
     * @param valor contiene el valor que va a almacenar dicha clave
     */
    public void guardarInt(String clave, int valor){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt(clave,valor);
        editor.apply();
    }

    /**
     * Es un metodo que se encarga de retornar el valor que contiene dicha clave pasada por paramertro
     * @param clave es un String
     * @return retorna un Entero
     */
    public int recuperarInt(String clave){
        return preferencias.getInt(clave,-1);
    }
    /**
     * Es un metodo que se encarga de retornar el valor que contiene dicha clave pasada por paramertro
     * @param clave es un String
     * @return retorna un String
     */
    public String recuperarString(String clave){
        return preferencias.getString(clave, null);
    }
}
