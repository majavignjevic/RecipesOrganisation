module com.projekt.organizacijarecepata {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.slf4j;

    opens com.projekt.organizacijarecepata.Service to javafx.fxml;
    exports com.projekt.organizacijarecepata.Service;
    exports com.projekt.organizacijarecepata.Controller;
    exports com.projekt.organizacijarecepata.entiteti;
    opens com.projekt.organizacijarecepata.Controller to javafx.fxml;

}