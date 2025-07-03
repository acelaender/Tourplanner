module at.fhtw.swen.tourplanner.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires org.apache.logging.log4j;


    opens at.fhtw.swen.tourplanner.tourplanner to javafx.fxml, spring.core, spring.beans;
    opens at.fhtw.swen.tourplanner.tourplanner.viewModel to spring.core, spring.beans;
    exports at.fhtw.swen.tourplanner.tourplanner;
    exports at.fhtw.swen.tourplanner.tourplanner.Controller;
    opens at.fhtw.swen.tourplanner.tourplanner.Controller to javafx.fxml, spring.beans, spring.core;
}