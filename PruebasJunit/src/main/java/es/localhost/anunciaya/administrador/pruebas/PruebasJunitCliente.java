package es.localhost.anunciaya.administrador.pruebas;

import es.localhost.anunciaya.administrador.util.MetodosCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PruebasJunitCliente {
    private MetodosCliente m = new MetodosCliente();
    @Test
    void insertarUsuario(){
        String[]p1 = {"manuel","kjhakjhd","mm_ll","abrete1234","2004-01-20","mamma@gmail.com","88238182"};// dato valido
        Assertions.assertNotEquals(-1,m.insertUsuario(p1));
        String[]p2 = {"878723","kjhakjhd","mm_ll","papapa1234","2004-01-20","admin@anunciaya.es","88238182"};// dato no valido
        Assertions.assertNotEquals(-1,m.insertUsuario(p2));
    }

    @Test
    void verificarAuthCliente(){
        String[] p1 = {"cperez", "$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a4"};// dato valido
        Assertions.assertTrue(m.verificarAuthCliente(p1));
        String[] p2 = {"cperez", "1234"};// dato no valido
        Assertions.assertTrue(m.verificarAuthCliente(p2));
    }
    @Test
    void getCategorias(){
        Assertions.assertNotNull(m.getCategorias());
    }
    @Test
    void getAnunciosInicio(){
        String []p1 = {"1"};
        Assertions.assertNotNull(m.getAnunciosInicio(p1));
        String []p2 = {"-1"};
        Assertions.assertNotNull(m.getAnunciosInicio(p2));
    }
    @Test
    void getUsuarioDataId(){
        String []p1 = {"1"};
        Assertions.assertNotNull(m.getUsuarioDataId(p1));
        String []p2 = {"-1"};
        Assertions.assertNotNull(m.getUsuarioDataId(p2));
    }
    @Test
    void getAnunciosIdCategoria(){
        String []p1 = {"1"};
        Assertions.assertNotNull(m.getAnunciosIdCategoria(p1));
        String []p2 = {"-1"};
        Assertions.assertNotNull(m.getAnunciosIdCategoria(p2));
    }
    @Test
    void getAnunciosUbicacionCategoria(){
        String []p1 = {"Hinojosa del Duque","1"};
        Assertions.assertNotNull(m.getAnunciosUbicacionCategoria(p1));
        String []p2= {"NoExiste","1"};
        Assertions.assertNotNull(m.getAnunciosUbicacionCategoria(p2));
        String []p3= {"Pozoblanco","-1"};
        Assertions.assertNotNull(m.getAnunciosUbicacionCategoria(p3));
    }
    @Test
    void getCategoriaId(){
        String []p1 = {"Pozoblanco"};
        Assertions.assertNotEquals(-1,m.getCategoriaId(p1));
        String []p2 = {"NoExiste"};
        Assertions.assertNotEquals(-1,m.getCategoriaId(p2));
    }
    @Test
    void getIdNewAnuncioUsuario(){
        String []p1 = {"1"};
        Assertions.assertNotEquals(-1,m.getIdNewAnuncioUsuario(p1));
        String []p2 = {"-1"};
        Assertions.assertNotEquals(-1,m.getIdNewAnuncioUsuario(p2));
    }
    @Test
    void insertPedido(){
        String []p1 = {"1,1,Caridad 48, Pozoblanco, 14270"};
        Assertions.assertTrue(m.insertPedido(p1));
        String []p2 = {"1,1,Caridad 48, Pozoblanco, 14270"}; // pedido del Mismo
        Assertions.assertTrue(m.insertPedido(p2));
        String []p3 = {",,Caridad 48, Pozoblanco, 14270"}; // pedido del Mismo
        Assertions.assertTrue(m.insertPedido(p3));
    }
    @Test
    void getIdUser(){
        String[] p1 = {"cperez"};// dato valido
        Assertions.assertNotNull(m.getIdUser(p1));
        String[] p2 = {"anonimo"};// dato no valido
        Assertions.assertNotNull(m.getIdUser(p2));
    }

    @Test
    void getAnunciosUbicacion(){
        String[] p1 = {"Córdoba", "1"};// dato valido
        Assertions.assertNotNull(m.getAnunciosUbicacion(p1));
        String[] p2 = {"Pozoblanco", "1"};// dato no valido
        Assertions.assertNotNull(m.getAnunciosUbicacion(p2));
    }
    @Test
    void updateAnuncioUploadImagen(){
        String[] p1 = {"https://google.es/fotoEjemplo.jpg", "1"};// dato valido
        Assertions.assertNotNull(m.updateAnuncioUploadImagen(p1));
        String[] p2 = {"https://google.es/fotoEjemplo.jpg", "-1"};// dato valido
        Assertions.assertNotNull(m.updateAnuncioUploadImagen(p2));
        String[] p3 = {"", "1"};// dato valido
        Assertions.assertNotNull(m.updateAnuncioUploadImagen(p3));
    }
    @Test
    void getPedidos(){
        String[] p1 = {"1"};// dato valido
        Assertions.assertNotNull(m.getPedidos(p1));
        String[] p2 = {""};// dato valido
        Assertions.assertNotNull(m.getPedidos(p2));
        String[] p3 = {"-1"};// dato valido
        Assertions.assertNotNull(m.getPedidos(p3));
    }
    @Test
    void getEnvios(){
        String[] p1 = {"1"};// dato valido
        Assertions.assertNotNull(m.getEnvios(p1));
        String[] p2 = {""};// dato valido
        Assertions.assertNotNull(m.getEnvios(p2));
        String[] p3 = {"-1"};// dato valido
        Assertions.assertNotNull(m.getEnvios(p3));
    }

    @Test
    void getCategoriaDescripcion(){
        String[] p1 = {"1"};// dato valido
        Assertions.assertNotNull(m.getCategoriaDescripcion(p1));
        String[] p2 = {"8"};// dato no valido
        Assertions.assertNotNull(m.getCategoriaDescripcion(p2));
    }

    @Test
    void insertarAnuncio(){
        String []p1 = {"1", "2", "Ejemplo titulo", "Ejemplo descripcion", "Bueno", "Córdoba", "12", "http://host/img1.jpg;http://host/img2.jpg;"};
        Assertions.assertTrue(m.insertarAnuncio(p1));
        String []p2 = {"1", "2", "Ejemplo titulo", "Ejemplo descripcion", "Bueno", "Córdoba", "12"};
        Assertions.assertTrue(m.insertarAnuncio(p2));
    }

    @Test
    void getAnunciosIdUsuario(){
        String[] p1 = {"1"};// dato valido
        Assertions.assertNotNull(m.getAnunciosIdUsuario(p1));
        String[] p2 = {"8"};// dato no valido
        Assertions.assertNotNull(m.getAnunciosIdUsuario(p2));
    }
    @Test
    void ActualizarContasena(){
        String[]p1 = {"1","1234"};// dato valido
        Assertions.assertTrue(m.ActualizarContasena(p1));
        String[]p2 = {"-11","1234"};// dato no valido
        Assertions.assertTrue(m.ActualizarContasena(p2));
    }
    @Test
    void deleteAnuncio(){
        String[]p1 = {"1"};// dato valido
        Assertions.assertNotEquals("false",m.deleteAnuncio(p1));
        String[]p2 = {"-11"};// dato no valido
        Assertions.assertNotEquals("false",m.deleteAnuncio(p2));
    }

    // $id_usuario, $nombre, $apellidos, $telefono, $email, $fecha_nac, $nomb_usu
    @Test
    void ActualizarUsuario(){
        String []p1 = {"1", "Pepe", "Lopez", "675432190", "", "", ""};
        Assertions.assertTrue(m.ActualizarUsuario(p1));
        String []p2 = {"0", "Pepe", "Lopez", "675432190", "", "", ""};
        Assertions.assertTrue(m.ActualizarUsuario(p2));
    }

    @Test
    void updateAnuncio(){
        String []p1 = {"1", "Titulo anuncio", "Descripcion nueva", "Bueno", "Córdoba", "12", "http://host/img1.jpg;http://host/img2.jpg;", "1"};
        Assertions.assertTrue(m.ActualizarUsuario(p1));
        String []p2= {"0", "Titulo anuncio", "Descripcion nueva", "Bueno", "Córdoba", "12", "http://host/img1.jpg;http://host/img2.jpg;", "1"};
        Assertions.assertTrue(m.ActualizarUsuario(p2));
    }
}
