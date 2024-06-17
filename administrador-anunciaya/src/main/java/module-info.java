module es.localhost.anunciaya.administrador {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires com.jfoenix;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;
    requires org.junit.jupiter.api;
    requires junit;

    opens es.localhost.anunciaya.administrador to javafx.fxml;
    exports es.localhost.anunciaya.administrador;
    exports es.localhost.anunciaya.administrador.util;
    opens es.localhost.anunciaya.administrador.util to javafx.fxml;
    exports es.localhost.anunciaya.administrador.ViewsControllers;
    opens es.localhost.anunciaya.administrador.ViewsControllers to javafx.fxml;
}