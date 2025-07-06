package at.fhtw.swen.tourplanner.tourplanner.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Location {
    private final StringProperty name;
    private String lon;
    private String lat;

    public Location(String name, String lon, String lat) {
        this.name = new SimpleStringProperty(name);
        this.lon = lon;
        this.lat = lat;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty(){
        return name;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
