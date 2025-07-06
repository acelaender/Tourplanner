package at.fhtw.swen.tourplanner.tourplanner.model;

import jakarta.persistence.Entity;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;


public class TourEntry {

    private int id;
    private final StringProperty name;
    private final StringProperty description;
    private final ObjectProperty<Location> from;
    private final ObjectProperty<Location> to;
    private final StringProperty transportType;
    private final ObjectProperty<Route> route;
    private final StringProperty routeInfo;




    public TourEntry(int id, String name, String description, Location from, Location to, String transportType, Route route, String routeInfo) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.from = new SimpleObjectProperty<>(from);
        this.to = new SimpleObjectProperty<>(to);
        this.transportType = new SimpleStringProperty(transportType);
        this.route = new SimpleObjectProperty<>(route);
        this.routeInfo = new SimpleStringProperty(routeInfo);
    }

    public TourEntry(){
        this.id = -1;
        this.name = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.from = new SimpleObjectProperty<>(new Location("", "",""));
        this.to = new SimpleObjectProperty<>(new Location("", "", ""));
        this.transportType = new SimpleStringProperty("");
        this.route = new SimpleObjectProperty<>();
        this.routeInfo = new SimpleStringProperty("");
    }

    public ObservableList<String> getAvailableTransportTypes() {
        return FXCollections.observableArrayList("driving-car", "cycling-regular", "foot-walking", "wheelchair");
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Location getFrom() {
        return from.get();
    }

    public ObjectProperty<Location> fromProperty() {
        return from;
    }

    public void setFrom(Location from) {
        this.from.set(from);
    }

    public Location getTo() {
        return to.get();
    }

    public ObjectProperty<Location> toProperty() {
        return to;
    }

    public void setTo(Location to) {
        this.to.set(to);
    }

    public String getTransportType() {
        return transportType.get();
    }

    public StringProperty transportTypeProperty() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType.set(transportType);
    }

    public Route getRoute() {
        return route.get();
    }

    public ObjectProperty<Route> routeProperty() {
        return route;
    }

    public void setRoute(Route route) {
        this.route.set(route);
    }

    public String getRouteInfo() {
        return routeInfo.get();
    }

    public StringProperty routeInfoProperty() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo.set(routeInfo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return "Tour: " + name.get() + ", From: " + from.get() + ", To: " + to.get();
    }
}
