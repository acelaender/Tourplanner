package at.fhtw.swen.tourplanner.tourplanner.Controller;

import at.fhtw.swen.tourplanner.tourplanner.MainApplication;
import at.fhtw.swen.tourplanner.tourplanner.Service.GeoCodingService;
import at.fhtw.swen.tourplanner.tourplanner.model.Location;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.viewModel.AddTourViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AddTourController {

    //Class Workers
    @Autowired
    private AddTourViewModel viewModel;
    private static final Logger logger = LogManager.getLogger(AddTourController.class);

    // Input Form Fields
    //The Tour Details Fields
    @FXML private TextField tourNameField;
    @FXML private TextField descriptionField;
    @FXML private TextField fromField;
    @FXML private ListView<Location> fromSuggestions;
    @FXML private TextField toField;
    @FXML private ListView<Location> toSuggestions;
    @FXML private ComboBox<String> transportTypeField;
    @FXML private TextField routeInfoField;

    public AddTourController(AddTourViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setContext(TourEntry tour){
        viewModel.setEditTour(tour);
        bind();
    }

    @FXML
    public void initialize(){
        //Connecting Form Location fields to viewmodel
        fromSuggestions.setItems(viewModel.getStartLocations());
        toSuggestions.setItems(viewModel.getEndLocations());
        viewModel.fromProperty().bind(fromSuggestions.getSelectionModel().selectedItemProperty());
        viewModel.toProperty().bind(toSuggestions.getSelectionModel().selectedItemProperty());

        //Listening to userinput for viewModel Lookup of Start Location
        fromField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() < 3){
                return;
            }
            if (!newVal.isBlank()) {
                CompletableFuture.runAsync(() -> {
                    viewModel.fetchStartSuggestions(newVal);
                    fromSuggestions.setVisible(true);
                });
            }
        });
        //Click-Event for selecting a Starting Location
        fromSuggestions.setOnMouseClicked(e -> {
            Location selected = fromSuggestions.getSelectionModel().getSelectedItem();
            fromField.setText(selected.getName());
            fromSuggestions.setVisible(false);
        });

        //Listening for userinput for viewModel-Lookup of End-Location
        toField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() < 3){
                return;
            }
            if (!newVal.isBlank()) {
                CompletableFuture.runAsync(() ->{
                    try {
                        viewModel.fetchEndSuggestions(newVal);
                        toSuggestions.setVisible(true);
                    } catch (Exception e) {
                        logger.error(e);
                    }
                });
            }
        });
        //Click-Event for selecting End Location
        toSuggestions.setOnMouseClicked(e -> {
            Location selected = toSuggestions.getSelectionModel().getSelectedItem();
            toField.setText(selected.getName());
            toSuggestions.setVisible(false);
        });
        bind();
    }

    private void bind(){
        //Binding the rest of the Input fields
        tourNameField.textProperty().bindBidirectional(viewModel.nameProperty());
        descriptionField.textProperty().bindBidirectional(viewModel.descriptionProperty());
        //Setting transport types
        transportTypeField.setItems(viewModel.getAvailableTransportTypes());
        //
        transportTypeField.valueProperty().bindBidirectional(viewModel.transportTypeProperty());
        routeInfoField.textProperty().bindBidirectional(viewModel.routeInfoProperty());
    }

    @FXML
    public void handleSave(){
        try {
            viewModel.saveTour();
        } catch (Exception e) {}
        ((Stage) tourNameField.getScene().getWindow()).close();
    }
}
