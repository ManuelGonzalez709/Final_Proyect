package es.localhost.anunciaya.administrador.ViewsControllers;

import es.localhost.anunciaya.administrador.Categoria;
import es.localhost.anunciaya.administrador.Util;
import es.localhost.anunciaya.administrador.util.Metodos;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para gestionar las operaciones relacionadas con la vista de categorías.
 * Implementa {@link Initializable} para manejar la inicialización de componentes FXML.
 *
 * @author AnunciaYa
 */
public class CategoriaController implements Initializable {

    /**
     * Lista observable que almacena las categorías.
     */
    private ObservableList<Categoria> categoriasList;

    /**
     * Tabla que muestra las categorías.
     */
    @FXML
    private TableView<Categoria> tbCategorias;

    /**
     * Columna de la tabla para mostrar el ID de la categoría.
     */
    @FXML
    private TableColumn<Categoria, Integer> tcIdCategoria;

    /**
     * Columna de la tabla para mostrar la descripción de la categoría.
     */
    @FXML
    private TableColumn<Categoria, String> tcDescrip;

    /**
     * Método de inicialización para configurar la tabla y cargar los datos al abrir la vista.
     *
     * @param url no se utiliza en este contexto.
     * @param resourceBundle no se utiliza en este contexto.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrepararTabla();
        CargarDatosTabla();
    }

    /**
     * Carga los datos de las categorías en la tabla desde la base de datos.
     */
    public void CargarDatosTabla() {
        Metodos me = new Metodos();
        categoriasList = me.getAllCategorias(new String[]{""});
        tbCategorias.setItems(categoriasList);
    }

    /**
     * Prepara la configuración de la tabla, estableciendo las propiedades de las columnas
     * y haciendo la columna de descripción editable.
     */
    private void PrepararTabla() {
        Metodos me = new Metodos();

        tcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcIdCategoria.setResizable(false);

        tcDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcDescrip.setResizable(false);

        tbCategorias.setEditable(true);
        tcDescrip.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        tcDescrip.setOnEditCommit(event -> {
            Categoria categoria = event.getRowValue();
            String nuevaDescripcion = event.getNewValue();

            if (categoria != null) {
                categoria.setDescripcion(nuevaDescripcion);

                if (me.updateCategoria(new String[]{String.valueOf(categoria.getId()), nuevaDescripcion})) {
                    tbCategorias.refresh();
                    Util.mostrarDialogo("MENSAJE_INFO", "Categoría actualizada",
                            "Se ha actualizado la descripción de la categoría correctamente.", Alert.AlertType.INFORMATION);
                } else {
                    Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo actualizar la categoría",
                            "La descripción de la categoría no se pudo actualizar.", Alert.AlertType.ERROR);
                }
            }
        });
    }

    /**
     * Maneja el evento de clic en el botón para agregar una nueva categoría.
     * Abre un diálogo modal para insertar una nueva categoría.
     */
    @FXML
    private void onAddCatClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/localhost/anunciaya/administrador/insert_categoria.fxml"));
            Parent root = loader.load();

            InsertCategoController controller = loader.getController();
            controller.setCategoController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Insertar Categoría");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            Stage primaryStage = (Stage) tbCategorias.getScene().getWindow();
            stage.initOwner(primaryStage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja el evento de clic en el botón para borrar la categoría seleccionada.
     * Muestra un diálogo de confirmación y elimina la categoría si se confirma.
     */
    @FXML
    private void onBorrarCatClick() {
        Metodos m = new Metodos();
        Categoria selectedCategoria = tbCategorias.getSelectionModel().getSelectedItem();

        if (selectedCategoria != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar la categoría?");
            alert.setContentText("Esta acción no se puede deshacer.");

            String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);
            alert.getDialogPane().getStyleClass().add("custom-alert");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (!m.tieneAnunciosCategoria(new String[]{String.valueOf(selectedCategoria.getId())})) {
                    if (m.deleteCategoria(new String[]{String.valueOf(selectedCategoria.getId())})) {
                        tbCategorias.getItems().remove(selectedCategoria);
                        tbCategorias.refresh();
                        Util.mostrarDialogo("MENSAJE_INFO", "Categoría eliminada",
                                "La categoría ha sido eliminada correctamente.", Alert.AlertType.INFORMATION);
                    } else {
                        Util.mostrarDialogo("MENSAJE_WARNING", "No se pudo eliminar la categoría",
                                "La categoría no pudo ser eliminada del servidor.", Alert.AlertType.WARNING);
                    }
                } else {
                    Util.mostrarDialogo("Operación no permitida", "Eliminación de categoría no permitida",
                            "La categoría seleccionada no puede ser eliminada ya que está asociada a múltiples anuncios.", Alert.AlertType.ERROR);
                }
            } else {
                Util.mostrarDialogo("MENSAJE_INFO", "Eliminación cancelada",
                        "La eliminación de la categoría ha sido cancelada.", Alert.AlertType.INFORMATION);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "No se seleccionó ninguna categoría",
                    "Por favor, selecciona una categoría para eliminar.", Alert.AlertType.WARNING);
        }
    }
}
