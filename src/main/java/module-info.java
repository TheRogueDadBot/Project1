/**
 * Module info for com.sotware1.project1 module
 */
module com.software1.project1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.software1.project1 to javafx.fxml;
    opens com.software1.project1.controller to javafx.fxml;
    opens com.software1.project1.model to javafx.base;

    exports com.software1.project1;
    exports com.software1.project1.controller;
    exports com.software1.project1.model;
}
