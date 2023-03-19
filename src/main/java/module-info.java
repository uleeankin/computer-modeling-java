module com.uleeankin.computermodelingjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.uleeankin.computermodelingjava to javafx.fxml;
    exports com.uleeankin.computermodelingjava.lab5.beta;
    opens com.uleeankin.computermodelingjava.lab5.beta to javafx.fxml;
    exports com.uleeankin.computermodelingjava;
    exports com.uleeankin.computermodelingjava.utils;
    opens com.uleeankin.computermodelingjava.utils to javafx.fxml;
    exports com.uleeankin.computermodelingjava.lab5.weibull;
    opens com.uleeankin.computermodelingjava.lab5.weibull to javafx.fxml;
    exports com.uleeankin.computermodelingjava.lab5;
    opens com.uleeankin.computermodelingjava.lab5 to javafx.fxml;
    exports com.uleeankin.computermodelingjava.lab6;
    opens com.uleeankin.computermodelingjava.lab6 to javafx.fxml;
    exports com.uleeankin.computermodelingjava.lab7;
    opens com.uleeankin.computermodelingjava.lab7 to javafx.fxml;
}