package at.fhtw.swen.tourplanner.tourplanner.Controller;

import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.viewModel.AddTourViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddTourController {

    @Autowired
    private AddTourViewModel viewModel;

    // Input Form Fields
    //The Tour Details Fields
    @FXML private TextField tourNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField fromField;
    @FXML private TextField toField;
    @FXML private ComboBox<String> transportTypeField;
    @FXML private TextField routeInfoField;

    @FXML
    public void initialize(){
        tourNameField.textProperty().bindBidirectional(viewModel.nameProperty());
        descriptionField.textProperty().bindBidirectional(viewModel.descriptionProperty());
        fromField.textProperty().bindBidirectional(viewModel.fromProperty());
        toField.textProperty().bindBidirectional(viewModel.toProperty());
        //Setting transport types
        transportTypeField.setItems(viewModel.getAvailableTransportTypes());
        //
        transportTypeField.valueProperty().bindBidirectional(viewModel.transportTypeProperty());
        routeInfoField.textProperty().bindBidirectional(viewModel.routeInfoProperty());

    }

    @FXML
    public void handleSave(){
        viewModel.saveTour();
        ((Stage) tourNameField.getScene().getWindow()).close();
    }
}
