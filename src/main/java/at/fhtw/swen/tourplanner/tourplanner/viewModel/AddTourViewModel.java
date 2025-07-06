package at.fhtw.swen.tourplanner.tourplanner.viewModel;

import at.fhtw.swen.tourplanner.tourplanner.Service.GeoCodingService;
import at.fhtw.swen.tourplanner.tourplanner.Service.TourService;
import at.fhtw.swen.tourplanner.tourplanner.dal.repository.TourRepositoryDemo;
import at.fhtw.swen.tourplanner.tourplanner.model.Location;
import at.fhtw.swen.tourplanner.tourplanner.model.Route;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddTourViewModel {

    //GeocodingService for lookup of tours and places
    private final GeoCodingService geoCodingService;
    private final TourService tourService;

    private TourEntry tourEntry = new TourEntry();
    private static final Logger logger = LogManager.getLogger(MainViewModel.class);
    private final TourRepositoryDemo tourRepositoryDemo = new TourRepositoryDemo();

    //Location Suggestions & Selected sug
    private final ObservableList<Location> startLocations = FXCollections.observableArrayList();
    private final ObservableList<Location> endLocations = FXCollections.observableArrayList();

    private TourEntry editTour;

    public AddTourViewModel(TourService tourService, GeoCodingService geoCodingService) {
        this.tourService = tourService;
        this.geoCodingService = geoCodingService;
    }

    public void setEditTour(TourEntry tour){
        this.editTour = tour;
        this.tourEntry = tour;
    }



    public void saveTour() throws  Exception{

        Route route = geoCodingService.getRoute(tourEntry.getFrom(), tourEntry.getTo(), tourEntry.getTransportType());
        tourEntry.setRoute(route);

        logger.info("saving tour: " + tourEntry);

        if(editTour == null) {
            tourService.save(tourEntry);
        } else {
            logger.info("overwriting tour: " + tourEntry);
            tourService.overwrite(tourEntry);
        }

    }

    public void fetchStartSuggestions(String query){
        List<Location> results;
        try {
            results = geoCodingService.searchPlace(query);
        } catch (Exception e){
            logger.error(e);
            return;
        }
        Platform.runLater(() -> {
            startLocations.setAll(results); // triggers UI update
        });
    }

    public void fetchEndSuggestions(String query) throws Exception{
        List<Location> results = geoCodingService.searchPlace(query);
        Platform.runLater(() -> {
            endLocations.setAll(results); // triggers UI update
        });
    }


    public ObservableList<String> getAvailableTransportTypes(){
        return new TourEntry().getAvailableTransportTypes();
    }

    public StringProperty nameProperty() { return tourEntry.nameProperty(); }
    public StringProperty descriptionProperty() { return tourEntry.descriptionProperty(); }
    public ObjectProperty<Location> fromProperty() { return tourEntry.fromProperty(); }
    public ObjectProperty<Location> toProperty() { return tourEntry.toProperty(); }
    public StringProperty transportTypeProperty() { return tourEntry.transportTypeProperty(); }
    public StringProperty routeInfoProperty() { return tourEntry.routeInfoProperty(); }
    public ObservableList<Location> getStartLocations() { return startLocations; }
    public ObservableList<Location> getEndLocations() { return endLocations; }
}
