package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la vista de inserción de una nueva categoría.
 * Maneja la lógica de inserción de una nueva categoría en la base de datos.
 *
 * @author AnunciaYa
 */
public class InsertCategoController {

    /**
     * Controlador de la vista principal de categorías, utilizado para refrescar la tabla de categorías
     * después de la inserción de una nueva.
     */
    private CategoriaController categoriaController;

    /**
     * Campo de texto para ingresar el nombre de la nueva categoría.
     */
    @FXML
    private TextField tfNombCategoria;

    /**
     * Botón para cancelar la operación de inserción y cerrar la ventana.
     */
    @FXML
    private Button btCancelar;

    /**
     * Establece el controlador principal de categorías.
     * Este método se utiliza para enlazar este controlador con el controlador de la vista principal.
     *
     * @param categoController el controlador de la vista principal de categorías.
     */
    public void setCategoController(CategoriaController categoController) {
        this.categoriaController = categoController;
    }

    /**
     * Maneja el evento de clic en el botón "Aceptar" para añadir una nueva categoría.
     * Verifica que el campo de texto no esté vacío, intenta insertar la categoría,
     * y muestra mensajes de éxito o error según el resultado.
     */
    @FXML
    private void onAceptarClick() {
        Metodos m = new Metodos();

        // Verifica que el campo de nombre de categoría no esté vacío
        if (!tfNombCategoria.getText().isEmpty()) {
            // Intenta insertar la nueva categoría en la base de datos
            if (m.insertCategoria(new String[]{tfNombCategoria.getText()})) {
                // Inserción exitosa: muestra un mensaje y refresca la tabla de categorías
                Util.mostrarDialogo("MENSAJE_INFO", "Categoría añadida",
                        "Se ha registrado la nueva categoría correctamente.", Alert.AlertType.INFORMATION);
                categoriaController.CargarDatosTabla();
                Cerrar();
            } else {
                // Error en la inserción: muestra un mensaje de error
                Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo añadir la categoría",
                        "Ha surgido un error con la categoría que se ha intentado añadir.", Alert.AlertType.ERROR);
            }
        } else {
            // Campo vacío: muestra un mensaje de advertencia
            Util.mostrarDialogo("MENSAJE_WARNING", "Hay campos vacíos",
                    "Por favor comprueba que todos los campos están completos antes de añadir una categoría.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Maneja el evento de clic en el botón "Cancelar" para cerrar la ventana.
     * Llama al método {@link #Cerrar()} para cerrar la ventana y refrescar la tabla de categorías.
     */
    public void btCancelar() {
        Cerrar();
    }

    /**
     * Cierra la ventana actual y refresca la tabla de categorías en la vista principal.
     */
    private void Cerrar() {
        // Refresca la tabla de categorías en el controlador principal
        categoriaController.CargarDatosTabla();

        // Obtiene el Stage actual a partir del botón "Cancelar" y lo cierra
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
}
