package at.fhtw.swen.tourplanner.tourplanner.Controller;

import at.fhtw.swen.tourplanner.tourplanner.FocusChangedListener;
import at.fhtw.swen.tourplanner.tourplanner.MainApplication;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import at.fhtw.swen.tourplanner.tourplanner.viewModel.MainViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {
    private final MainViewModel mainViewModel;

    @FXML
    TableView<TourEntry> tourTable = new TableView<>();
    @FXML TableView<TourLog> tourLogTable = new TableView<>();
    @FXML TableColumn<TourLog, LocalDate> date;
    @FXML TableColumn<TourLog, Integer> duration;
    @FXML TableColumn<TourLog, Integer> distance;
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

    public void addTour() {
        mainViewModel.addTour();
        tourTable.setItems(mainViewModel.getTourEntries());
    }

    public void deleteCurrentTour() {
        mainViewModel.deleteTour();
        tourTable.setItems(mainViewModel.getTourEntries());
    }

    public void addTourLog() throws IOException {
        mainViewModel.addTourLog();
        tourLogTable.setItems(mainViewModel.getTourLogs());
    }


    public void deleteTour() {
        mainViewModel.deleteTour();
    }
    public void editTour() {
        mainViewModel.editTour();
    }
    public void exportTour(){mainViewModel.exportTour();}
    public void exportTourReport(){mainViewModel.exportTourReport();}
    public void exportSummaryTourReport(){mainViewModel.exportSummaryTourReport();}

    public void showMap(){
        mainViewModel.createMap();
        MainApplication.showMap();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        mainViewModel.initialize(url, resourceBundle);
        mainViewModel.setDemoItems();
        tourTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tourLogTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        duration.setCellValueFactory(cellData -> cellData.getValue().timeHoursProperty().asObject());
        distance.setCellValueFactory(cellData -> cellData.getValue().distanceProperty().asObject());

        //On select update fields
        mainViewModel.addListener(new FocusChangedListener() {
            @Override
            public void requestFocusChange(String name) {
                if(mainViewModel.getSelectedTour() != null) {
                    tourNameField.textProperty().bind(mainViewModel.getSelectedTour().nameProperty());
                    descriptionField.textProperty().bind(mainViewModel.getSelectedTour().descriptionProperty());
                    fromField.textProperty().bind(mainViewModel.getSelectedTour().getFrom().nameProperty());
                    toField.textProperty().bind(mainViewModel.getSelectedTour().getTo().nameProperty());
                    distanceField.textProperty().bind(mainViewModel.getSelectedTour().getRoute().distanceProperty().asString());
                    durationField.textProperty().bind(mainViewModel.getSelectedTour().getRoute().durationProperty().asString());
                    transportTypeField.textProperty().bind(mainViewModel.getSelectedTour().transportTypeProperty());
                    routeInfoField.textProperty().bind(mainViewModel.getSelectedTour().routeInfoProperty());
                    tourLogTable.setItems(mainViewModel.getTourLogs());
                }else{
                }
            }
        });

        //Filling the available Tours Table
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        tourTable.setItems(mainViewModel.getTourEntries());

        //Listening to selected tour
        tourTable.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            mainViewModel.setSelectedTour(selected);
        });
    }
}
