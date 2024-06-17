package es.localhost.anunciaya.administrador.pruebas;


import es.localhost.anunciaya.administrador.User;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PruebasJunitAdmin {
    private Metodos m = new Metodos();
    @Test
    void updateContras(){
        String[]p1 = {"1","1234"};// dato valido
        Assertions.assertTrue(m.updateContras(p1));
        String[]p2 = {"-11","1234"};// dato no valido
        Assertions.assertTrue(m.updateContras(p2));
    }
    @Test
    void actualizarUsuario(){
        String[]p1 = {"1","ejemplo","Gonzalez","Perez","64121212","manuel5365@gmail.com","2004-01-20","ll_pppa"};// dato valido
        Assertions.assertTrue(m.actualizarUsuario(p1));
        String[]p2 = {"-1","ejemplo","Gonzalez","Perez","64121212","manuel5365@gmail.com","2004-01-20","ll_pppa"};// dato no valido
        Assertions.assertTrue(m.actualizarUsuario(p2));
        String[]p3 = {"","ejemplo","Gonzalez","Perez","64121212","manuel5365@gmail.com","2004-01-20","ll_pppa"};// dato no valido
        Assertions.assertTrue(m.actualizarUsuario(p3));
    }
    @Test
    void borrarUsuario(){
        String[]p1 = {"1"};// dato valido
        Assertions.assertTrue(m.borrarUsuario(p1));
        String[]p2 = {"-1"};// dato no valido
        Assertions.assertTrue(m.borrarUsuario(p2));
    }
    @Test
    void insertarUsuario(){
        String[]p1 = {"manuel","kjhakjhd","mm_ll","abrete1234","2004-01-20","mamma@gmail.com","88238182"};// dato valido
        Assertions.assertTrue(m.insertarUsuario(p1));
        String[]p2 = {"878723","kjhakjhd","mm_ll","papapa1234","2004-01-20","mamma2@gmail.com","88238182"};// dato valido
        Assertions.assertTrue(m.insertarUsuario(p2));
    }
    @Test
    void getIdUserByEmail(){
        String ema1 = "admin@anunciaya.es";// dato valido
        Assertions.assertEquals(25,m.getIdUserByEmail(ema1));
        String ema2 = "noexistee@gmail.com";// dato valido
        Assertions.assertEquals(null,m.getIdUserByEmail(ema2));
    }
    @Test
    void getAllAnuncios(){
        String[] p1 = {};
        Assertions.assertNotNull(m.getAllAnuncios(p1));
    }
    @Test
    void AuthAdmin(){
        String[] p1 = {"admin@anunciaya.es", "$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a"};
        Assertions.assertTrue(m.AuthAdmin(p1));
        String[] p2 = {"admin@anunciaya.es", "1234"};
        Assertions.assertTrue(m.AuthAdmin(p2));
    }
    @Test
    void deleteAnuncio(){
        String[] p1 = {"-1"};
        Assertions.assertTrue(m.deleteAnuncio(p1));
        String[] p2 = {"1"};
        Assertions.assertTrue(m.deleteAnuncio(p2));
    }
    @Test
    void getAllCategorias(){
        String[] p1 = {"-1"};
        Assertions.assertNotNull(m.getAllCategorias(p1));
        String[] p2 = {"1"};
        Assertions.assertNotNull(m.getAllCategorias(p2));
    }
    @Test
    void getAllUsers(){
        String[] p1 = {"1"};
        Assertions.assertNotNull(m.getAllUsers(p1));
        String[] p2 = {""};
        Assertions.assertNotNull(m.getAllUsers(p2));
    }
    @Test
    void insertCategoria(){
        String[] p1 = {"motoreta"};
        Assertions.assertTrue(m.insertCategoria(p1));
        String[] p2 = {"coches"};
        Assertions.assertTrue(m.insertCategoria(p2));
        String[] p3 = {""};
        Assertions.assertTrue(m.insertCategoria(p3));
    }
    @Test
    void deleteCategoria(){
        String[] p1 = {"1"};
        Assertions.assertTrue(m.deleteCategoria(p1));
        String[] p2 = {"-1"};
        Assertions.assertTrue(m.deleteCategoria(p2));
    }
    @Test
    void tieneAnunciosCategoria(){
        String[] p1 = {"2"};
        Assertions.assertTrue(m.tieneAnunciosCategoria(p1));
        String[] p2 = {"8"};
        Assertions.assertTrue(m.tieneAnunciosCategoria(p2));
    }

    @Test
    void updateCategoria(){
        String[] p1 = {"2", "Nuevo nombre categoria"};
        Assertions.assertTrue(m.updateCategoria(p1));
        String[] p2 = {"8", "Modificado"};
        Assertions.assertTrue(m.updateCategoria(p2));
    }

    @Test
    void getAllPedidos(){
        String[] p1 = {""};
        Assertions.assertNotNull(m.getAllPedidos(p1));
        String[] p2 = {""};
        Assertions.assertNotNull(m.getAllPedidos(p2));
    }
}
