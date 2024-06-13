package es.localhost.anunciaya.administrador.ViewsControllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

/**
 * Controlador para manejar un carrusel de imágenes en JavaFX.
 *
 * @author AnunciaYa
 */
public class CarrouselController {

    @FXML private ImageView imageView; // Vista de imagen donde se mostrará la imagen actual del carrusel

    private List<Image> images; // Lista de imágenes para mostrar en el carrusel
    private int currentIndex; // Índice de la imagen actual en la lista

    /**
     * Establece la lista de imágenes y muestra la primera imagen en el carrusel.
     * @param images Lista de imágenes para mostrar.
     */
    public void setImages(List<Image> images) {
        this.images = images;
        currentIndex = 0;
        updateImageView(); // Actualiza la vista de la imagen con la primera imagen
    }

    /**
     * Muestra la imagen anterior en el carrusel si es posible.
     */
    @FXML
    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            updateImageView();
        }
    }

    /**
     * Muestra la siguiente imagen en el carrusel si es posible.
     */
    @FXML
    private void showNextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            updateImageView();
        }
    }

    /**
     * Actualiza la vista de la imagen con la imagen actual del índice `currentIndex`.
     */
    private void updateImageView() {
        if (images != null && !images.isEmpty()) {
            imageView.setImage(images.get(currentIndex));
        }
    }
}
