package es.localhost.anunciaya.administrador.util;

/**
 * Clase que implementa un Singleton para almacenar y gestionar un conjunto de datos.
 *
 * @author AnunciaYa
 */
public class DataStore {

    /**
     * Instancia única de DataStore.
     */
    private static DataStore instance = new DataStore();

    /**
     * Datos almacenados en la instancia.
     */
    private String data;

    /**
     * Constructor privado para evitar instanciación externa.
     */
    private DataStore() {}

    /**
     * Devuelve la instancia única de DataStore.
     * @return La instancia única de DataStore.
     */
    public static DataStore getInstance() {
        return instance;
    }

    /**
     * Devuelve los datos almacenados.
     * @return Los datos almacenados.
     */
    public String getData() {
        return data;
    }

    /**
     * Establece los datos a almacenar.
     * @param data Los datos a almacenar.
     */
    public void setData(String data) {
        this.data = data;
    }
}
