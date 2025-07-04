package at.fhtw.swen.tourplanner.tourplanner.Controller;

import at.fhtw.swen.tourplanner.tourplanner.MainApplication;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.viewModel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {
    private final MainViewModel mainViewModel;

    @FXML
    TableView<TourEntry> tourTable = new TableView<>();
    @FXML
    TableColumn<TourEntry, String> nameColumn;
    @FXML
    TableColumn<TourEntry, String> descriptionColumn;
    @FXML
    StackPane contentPane;

    //The Tour Details Fields
    @FXML
    Label tourNameField;
    @FXML
    Label descriptionField;
    @FXML
    Label fromField;
    @FXML
    Label toField;
    @FXML
    Label distanceField;
    @FXML
    Label durationField;
    @FXML
    Label transportTypeField;
    @FXML
    Label routeInfoField;



    public MainController(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public void addTour() throws IOException {
        mainViewModel.addTour();
    }

    public void addTourLog() throws IOException {
        mainViewModel.addTourLog();
    }

    public void deleteTour() {
        mainViewModel.deleteTour();
    }
    public void editTour() {
        mainViewModel.editTour();
    }

    public void showMap(){
        MainApplication.showMap();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        mainViewModel.initialize(url, resourceBundle);
        mainViewModel.setDemoItems();
        tourTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Filling the available Tours Table
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        tourTable.setItems(mainViewModel.getTourEntries());

        //TODO -> should i select 1st Tour?
        //For now doing so
        //Binding The Current Tours to the fxml list
        tourNameField.textProperty().bind(mainViewModel.getSelectedTour().nameProperty());
        descriptionField.textProperty().bind(mainViewModel.getSelectedTour().descriptionProperty());
        fromField.textProperty().bind(mainViewModel.getSelectedTour().fromProperty());
        toField.textProperty().bind(mainViewModel.getSelectedTour().toProperty());
        distanceField.textProperty().bind(mainViewModel.getSelectedTour().distanceProperty().asString());
        durationField.textProperty().bind(mainViewModel.getSelectedTour().durationProperty().asString());
        transportTypeField.textProperty().bind(mainViewModel.getSelectedTour().transportTypeProperty());
        routeInfoField.textProperty().bind(mainViewModel.getSelectedTour().routeInfoProperty());

        //Listen to the selected Tour in the table
        tourTable.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            mainViewModel.setSelectedTour(selected);
            tourNameField.textProperty().unbind();
            tourNameField.textProperty().bind(mainViewModel.getSelectedTour().nameProperty());
        });



    }
}
