package es.localhost.anunciaya.administrador.ViewsControllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class CarrouselController {

    @FXML
    private ImageView imageView;

    private List<Image> images;
    private int currentIndex;

    public void setImages(List<Image> images) {
        this.images = images;
        currentIndex = 0;
        updateImageView();
    }

    @FXML
    private void showPreviousImage() {
        if (currentIndex > 0) {
            currentIndex--;
            updateImageView();
        }
    }

    @FXML
    private void showNextImage() {
        if (currentIndex < images.size() - 1) {
            currentIndex++;
            updateImageView();
        }
    }

    private void updateImageView() {
        if (images != null && !images.isEmpty()) {
            imageView.setImage(images.get(currentIndex));
        }
    }
}
