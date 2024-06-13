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

public class CategoriaController implements Initializable {
    private ObservableList<Categoria> categoriasList;
    @FXML private TableView<Categoria> tbCategorias;
    @FXML private TableColumn<Categoria, Integer> tcIdCategoria;
    @FXML private TableColumn<Categoria, String> tcDescrip;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrepararTabla();
        CargarDatosTabla();
    }

    public void CargarDatosTabla(){
        Metodos me = new Metodos();

        categoriasList = me.getAllCategorias(new String[]{""});
        tbCategorias.setItems(categoriasList);
    }

    private void PrepararTabla(){
        Metodos me = new Metodos();
        tcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id")); tcIdCategoria.setResizable(false);
        tcDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcion")); tcDescrip.setResizable(false);

        // Hacer la tabla y la columna de descripción editables
        tbCategorias.setEditable(true);
        tcDescrip.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        // Manejar el evento de edición de la celda
        tcDescrip.setOnEditCommit(event -> {
            Categoria categoria = event.getRowValue();
            String nuevaDescripcion = event.getNewValue();

            if (categoria != null) {
                categoria.setDescripcion(nuevaDescripcion);

                // Actualizar la base de datos
                if (me.updateCategoria(new String[]{String.valueOf(categoria.getId()), nuevaDescripcion})) {
                    // Actualizar la tabla si la actualización en la base de datos fue exitosa
                    tbCategorias.refresh();
                    Util.mostrarDialogo("MENSAJE_INFO", "Categoría actualizada", "Se ha actualizado la descripción de la categoría correctamente.", Alert.AlertType.INFORMATION);

                } else {
                    // Manejar el error (puedes mostrar un diálogo de error aquí)
                    Util.mostrarDialogo("MENSAJE_ERROR", "No se pudo actualizar la categoría", "La descripción de la categoría no se pudo actualizar.", Alert.AlertType.ERROR);
                }
            }
        });
    }

    @FXML private void onAddCatClick(){
        try {
            // Cargar el archivo FXML desde los recursos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/localhost/anunciaya/administrador/insert_categoria.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del FXML y configurar las imágenes
            InsertCategoController controller = loader.getController();
            controller.setCategoController(this);

            // Crear y configurar el Stage para el diálogo modal
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Insertar Categoría");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            // Establecer la ventana principal como propietario de la ventana emergente
            Stage primaryStage = (Stage) tbCategorias.getScene().getWindow(); // Reemplaza 'tbAnuncios' con cualquier nodo de tu ventana principal
            stage.initOwner(primaryStage);

            // Mostrar la ventana emergente y esperar hasta que se cierre
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void onBorrarCatClick() {
        Metodos m = new Metodos();
        Categoria selectedCategoria = tbCategorias.getSelectionModel().getSelectedItem();

        if (selectedCategoria != null) {
            // Alert de confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar la categoría?");
            alert.setContentText("Esta acción no se puede deshacer.");

            // Cargar la hoja de estilo
            String css = Util.class.getResource("/es/localhost/anunciaya/administrador/estilos/alert.css").toExternalForm();
            alert.getDialogPane().getStylesheets().add(css);

            // Aplicar una clase CSS personalizada al DialogPane
            alert.getDialogPane().getStyleClass().add("custom-alert");

            Optional<ButtonType> result = alert.showAndWait();

            // Si se pulso en OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if(!m.tieneAnunciosCategoria(new String[]{String.valueOf(selectedCategoria.getId())})){
                    if (m.deleteCategoria(new String[]{String.valueOf(selectedCategoria.getId())})) {
                        // Eliminar la categoria de la lista observable
                        tbCategorias.getItems().remove(selectedCategoria);
                        // Refrescar el TableView para reflejar los cambios
                        tbCategorias.refresh();
                        Util.mostrarDialogo("MENSAJE_INFO", "Categoría eliminada", "La categoría ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
                    } else {
                        // Manejar el caso en que la categoria no pudo ser eliminado
                        Util.mostrarDialogo("MENSAJE_WARNING", "No se pudo eliminar la categoría", "La categoría no pudo ser eliminado del servidor.", Alert.AlertType.WARNING);
                    }
                } else{
                    Util.mostrarDialogo("Operación no permitida", "Eliminación de categoría no permitida", "La categoría seleccionada no puede ser eliminada ya que está asociada a múltiples anuncios.", Alert.AlertType.ERROR);
                }
            } else {
                // Se hizo click en Cancel o cerró el diálogo
                Util.mostrarDialogo("MENSAJE_INFO", "Eliminación cancelada", "La eliminación de la categoría ha sido cancelada.", Alert.AlertType.INFORMATION);
            }
        } else {
            Util.mostrarDialogo("MENSAJE_WARNING", "No se seleccionó ninguna categoría", "Por favor, selecciona una categoría para eliminar.", Alert.AlertType.WARNING);
        }
    }


}

