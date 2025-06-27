module at.fhtw.swen.tourplanner.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.fhtw.swen.tourplanner.tourplanner to javafx.fxml;
    exports at.fhtw.swen.tourplanner.tourplanner;
}