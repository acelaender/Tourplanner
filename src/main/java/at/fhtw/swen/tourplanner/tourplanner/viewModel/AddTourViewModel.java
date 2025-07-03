package at.fhtw.swen.tourplanner.tourplanner.viewModel;

import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddTourViewModel {


    //TODO Way to not declare variables every time?
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty transportType = new SimpleStringProperty();
    //private final IntegerProperty duration = new SimpleIntegerProperty();
    private final StringProperty routeInfo = new SimpleStringProperty();
    private static final Logger logger = LogManager.getLogger(MainViewModel.class);

    public void saveTour(){
        //TODO save to repository class
        TourEntry tour = new TourEntry();
        tour.setName(name.get());
        tour.setDescription(description.get());
        tour.setFrom(from.get());
        tour.setTo(to.get());
        tour.setTransportType(transportType.get());
        tour.setDistance(10); //TODO distance needs to be calculated
        tour.setDuration(10); //TODO duration calculated????
        tour.setRouteInfo(routeInfo.get());

        //TODO business logic on tour creation goes here
        //Tourrepository.saveTour()
    }

    //TODO this right??
    public ObservableList<String> getAvailableTransportTypes(){
        return new TourEntry().getAvailableTransportTypes();
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty fromProperty() { return from; }
    public StringProperty toProperty() { return to; }
    public StringProperty transportTypeProperty() { return transportType; }
    public StringProperty routeInfoProperty() { return routeInfo; }
}
