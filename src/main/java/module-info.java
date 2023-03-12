module com.uleeankin.computermodelingjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.uleeankin.computermodelingjava to javafx.fxml;
    exports com.uleeankin.computermodelingjava.beta;
    opens com.uleeankin.computermodelingjava.beta to javafx.fxml;
    exports com.uleeankin.computermodelingjava;
    exports com.uleeankin.computermodelingjava.utils;
    opens com.uleeankin.computermodelingjava.utils to javafx.fxml;
    exports com.uleeankin.computermodelingjava.weibull;
    opens com.uleeankin.computermodelingjava.weibull to javafx.fxml;
}