module es.localhost.anunciaya.administrador {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires com.jfoenix;

    opens es.localhost.anunciaya.administrador to javafx.fxml;
    exports es.localhost.anunciaya.administrador;
    exports es.localhost.anunciaya.administrador.util;
    opens es.localhost.anunciaya.administrador.util to javafx.fxml;
    exports es.localhost.anunciaya.administrador.ViewsControllers;
    opens es.localhost.anunciaya.administrador.ViewsControllers to javafx.fxml;
}