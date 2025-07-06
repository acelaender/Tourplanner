package at.fhtw.swen.tourplanner.tourplanner.viewModel;

import at.fhtw.swen.tourplanner.tourplanner.Controller.AddTourController;
import at.fhtw.swen.tourplanner.tourplanner.Controller.AddTourLogController;
import at.fhtw.swen.tourplanner.tourplanner.FocusChangedListener;
import at.fhtw.swen.tourplanner.tourplanner.MainApplication;
import at.fhtw.swen.tourplanner.tourplanner.Service.TourExportService;
import at.fhtw.swen.tourplanner.tourplanner.Service.TourLogService;
import at.fhtw.swen.tourplanner.tourplanner.Service.TourService;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class MainViewModel implements Initializable {

    @Autowired
    private AnnotationConfigApplicationContext context;
    private final ObservableList<TourEntry> tourEntries = FXCollections.observableArrayList();
    private final ObjectProperty<TourEntry> selectedTour = new SimpleObjectProperty<>();
    private final ObjectProperty<TourLog> selectedTourLog = new SimpleObjectProperty<>();
    private final ObservableList<TourLog> tourLogs = FXCollections.observableArrayList();

    //Subscribers List
    private List<FocusChangedListener> focusChangedListeners = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(MainViewModel.class);
    private final TourService tourService;
    private final TourLogService tourLogService;
    private final TourExportService tourExportService;

    public MainViewModel(TourService tourService, TourLogService tourLogService, TourExportService tourExportService) {
        this.tourService = tourService;
        this.tourLogService = tourLogService;
        this.tourExportService = tourExportService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        selectedTour.set(null);
    }

    public ObservableList<TourEntry> getTourEntries() {
        tourEntries.clear();
        tourEntries.addAll(tourService.findAll());
        return tourEntries;
    }

    public ObservableList<TourLog> getTourLogs() {
        tourLogs.clear();
        tourLogs.addAll(tourLogService.findAllByTourEntryModel(tourService.findTourEntryModelById(selectedTour.get().getId())));
        return tourLogs;
    }

    public void addTour() {
        logger.debug("opening add-tour window");
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("add-tour.fxml"));
        loader.setControllerFactory(Controller -> context.getBean(AddTourController.class));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            logger.error(e);
            return;
        }

        Stage addTourDialogue = new Stage();
        addTourDialogue.setTitle("Add new Tour");
        addTourDialogue.initModality(Modality.APPLICATION_MODAL);
        addTourDialogue.setScene(new Scene(root));
        addTourDialogue.showAndWait();
    }

    public void addTourLog() throws IOException {
        logger.debug("opening add-Tour-Log dialogue");
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("add-tourLog.fxml"));
        loader.setControllerFactory(Controller -> context.getBean(AddTourLogController.class));
        Parent root = loader.load();

        AddTourLogController controller = loader.getController();
        controller.setTourModel(tourService.findTourEntryModelById(selectedTour.get().getId()));

        Stage addTourLogDialogue = new Stage();
        addTourLogDialogue.setTitle("Add a Log");
        addTourLogDialogue.initModality(Modality.APPLICATION_MODAL);
        addTourLogDialogue.setScene(new Scene(root));
        addTourLogDialogue.showAndWait();
    }

    public void deleteTour(){
        if(selectedTour.get()!=null){
            tourService.delete(selectedTour.get());
            selectedTour.set(null);
        } else {
            logger.info("No tour was selected");
            showAlert("Missing Tour", "No Tour was selected!", Alert.AlertType.WARNING);
        }

    }

    public void editTour(){
        if(selectedTour.get() == null){
            logger.warn("No tour was selected");
            return;
        }

        logger.debug("opening edit-tour window");
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("add-tour.fxml"));
        loader.setControllerFactory(Controller -> context.getBean(AddTourController.class));

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            logger.error(e);
            return;
        }
        AddTourController controller = loader.getController();
        controller.setContext(selectedTour.get());
        Stage addTourDialogue = new Stage();
        addTourDialogue.setTitle("Edit Tour");
        addTourDialogue.initModality(Modality.APPLICATION_MODAL);
        addTourDialogue.setScene(new Scene(root));
        addTourDialogue.showAndWait();
    }

    public void editTourLog(){
        if(selectedTour.get() == null || selectedTourLog == null){
            logger.warn("No tour or TourLog was selected");
            return;
        }

        logger.debug("opening edit-tourLog window");
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("add-tourLog.fxml"));
        loader.setControllerFactory(Controller -> context.getBean(AddTourLogController.class));

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            logger.error(e);
            return;
        }
        AddTourLogController controller = loader.getController();
        controller.setContext(selectedTourLog.get());
        controller.setTourModel(tourService.findTourEntryModelById(selectedTour.get().getId()));
        Stage addTourDialogue = new Stage();
        addTourDialogue.setTitle("Edit Tour");
        addTourDialogue.initModality(Modality.APPLICATION_MODAL);
        addTourDialogue.setScene(new Scene(root));
        addTourDialogue.showAndWait();
    }

    public void exportTour(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Tours");
        fileChooser.setInitialFileName(selectedTour.get().getName());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File file = fileChooser.showSaveDialog(new Stage());

        if(file != null){
            try {
                tourExportService.exportTourToFile(tourService.findTourEntryModelById(selectedTour.get().getId()), file);
                showAlert("Export successful", "Tour exported to: " + file.getAbsoluteFile(), Alert.AlertType.INFORMATION);
                logger.info("Exported tour: " + selectedTour);
            }catch(IOException e){
                showAlert("Export failed", e.getMessage(), Alert.AlertType.ERROR);
                logger.error(e);
            }
        }
    }
    public void exportTourReport(){
        //TODO
    }
    public void exportSummaryTourReport(){
        //TODO
    }

    private void showAlert(String title, String content, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void createMap(){
        try {
            Path path = Paths.get("src/main/resources/directions2.js");
            Files.writeString(path, "var directions = \n" + selectedTour.getValue().getRoute().getGeoJson() + "\n;", StandardOpenOption.CREATE);
        } catch (IOException e) {}
    }

    public void addListener(FocusChangedListener listener){ this.focusChangedListeners.add(listener); }


    public TourEntry getSelectedTour(){
        return selectedTour.get();
    }

    public void setSelectedTour(TourEntry tour){
        selectedTour.set(tour);
        for(var listener: this.focusChangedListeners){
            listener.requestFocusChange("Selected Item changed");
        }
    }


    public void setDemoItems(){
        /*
        tourEntries.add(new TourEntry("Tour1", "Description for this tour", "Starting Point", "Ending Point", "Car", 100, 5, "some random info"));
        tourEntries.add(new TourEntry("Tour2", "Description for this tour", "Starting Point", "Ending Point", "Bus", 50, 2, "some random info"));
        setSelectedTour(tourEntries.get(0));

         */
    }
}
