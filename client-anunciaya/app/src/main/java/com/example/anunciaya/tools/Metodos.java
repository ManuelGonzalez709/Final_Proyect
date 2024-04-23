package com.example.anunciaya.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

public class Metodos {
    private ServerComunication comunication;
    public Metodos() {comunication = new ServerComunication();}
    public int insertUsuario(String[]args){
        try{
            if(comunication.LanzarPeticion("Usuario","insertUsuario",args)){
                JSONObject jsonObject = new JSONObject(getRespuestaServer());
                if(jsonObject.getBoolean("data")){ // si sale true o se ha insertado
                    String []nomb_usu = {args[2]};
                    return getIdUser(nomb_usu);
                }else return -1;
            }else return -1;
        }catch (Exception e){return -1;}
    }
    public Boolean verificarAuthCliente(String[]args){
        try{
            comunication.LanzarPeticion("Auth","verificarAuthClient",args);
            JSONObject jsonObject = new JSONObject(getRespuestaServer());
            return jsonObject.getBoolean("data");
        }catch (Exception e){return false;}
    }
    public int getIdUser(String[]args){
        try{
            comunication.LanzarPeticion("Usuario","getIdUser",args);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(getRespuestaServer());
            String dataString = jsonNode.get("data").asText();
            JsonNode dataNode = objectMapper.readTree(dataString);
            return dataNode.get("id").asInt();
        }catch (Exception e){return -1;}
    }
    private String getRespuestaServer(){
        return comunication.getResultadoServer();
    }
}
