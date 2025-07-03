package at.fhtw.swen.tourplanner.tourplanner.viewModel;

import at.fhtw.swen.tourplanner.tourplanner.Controller.AddTourController;
import at.fhtw.swen.tourplanner.tourplanner.Controller.AddTourLogController;
import at.fhtw.swen.tourplanner.tourplanner.MainApplication;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Component
public class MainViewModel implements Initializable {

    @Autowired
    private AnnotationConfigApplicationContext context;
    private final ObservableList<TourEntry> tourEntries = FXCollections.observableArrayList();
    private final ObjectProperty<TourEntry> selectedTour = new SimpleObjectProperty<>();

    private static final Logger logger = LogManager.getLogger(MainViewModel.class);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public ObservableList<TourEntry> getTourEntries() {
        return tourEntries;
    }
    public void addTour() throws IOException {
        logger.debug("opening add-tour window");
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("add-tour.fxml"));
        loader.setControllerFactory(Controller -> context.getBean(AddTourController.class));
        Parent root = loader.load();

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

        Stage addTourLogDialogue = new Stage();
        addTourLogDialogue.setTitle("Add a Log");
        addTourLogDialogue.initModality(Modality.APPLICATION_MODAL);
        addTourLogDialogue.setScene(new Scene(root));
        addTourLogDialogue.showAndWait();
    }

    public void deleteTour(){
        //TODO
    }

    public void editTour(){
        //TODO
    }


    public ObjectProperty<TourEntry> getSelectedTourProperty(){
        return selectedTour;
    }

    public TourEntry getSelectedTour(){
        return selectedTour.get();
    }

    public void setSelectedTour(TourEntry tour){
        selectedTour.set(tour);
    }

    public void setContext(AnnotationConfigApplicationContext context){
        this.context = context;
    }
    public void setDemoItems(){
        tourEntries.add(new TourEntry("Tour1", "Description for this tour", "Starting Point", "Ending Point", "Car", 100, 5, "some random info"));
        tourEntries.add(new TourEntry("Tour2", "Description for this tour", "Starting Point", "Ending Point", "Bus", 50, 2, "some random info"));
        setSelectedTour(tourEntries.get(0));
    }
}
