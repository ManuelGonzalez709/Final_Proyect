package es.localhost.anunciaya.administrador.util;

public class DataStore {
    private static DataStore instance = new DataStore();
    private String data;

    private DataStore() {}

    public static DataStore getInstance() {
        return instance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
