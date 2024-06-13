package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertCategoController {
    private CategoriaController categoriaController;
    @FXML private TextField tfNombCategoria;
    @FXML private Button btCancelar;

    public void setCategoController(CategoriaController categoController) {
        this.categoriaController = categoController;
    }

    @FXML private void onAceptarClick() {
        Metodos m = new Metodos();
        if (!tfNombCategoria.getText().isEmpty()) {
            if(m.insertCategoria(new String[]{tfNombCategoria.getText()})){
                Util.mostrarDialogo("MENSAJE_INFO", "Categoría añadida", "Se ha registrado la nueva categoría correctamente.", Alert.AlertType.INFORMATION);
                categoriaController.CargarDatosTabla();
                Cerrar();
            } else{
                Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo añadir la categoría", "Ha surgido un error con la categoría que se ha intentado añadir.", Alert.AlertType.ERROR);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "Hay campos vacíos", "Por favor comprueba que todos los campos están completos antes de añadir una categoría.", Alert.AlertType.WARNING);
        }
    }

    public void btCancelar(){Cerrar();}
    private void Cerrar(){
        categoriaController.CargarDatosTabla();
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

}
