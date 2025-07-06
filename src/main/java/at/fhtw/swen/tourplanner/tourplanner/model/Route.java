package at.fhtw.swen.tourplanner.tourplanner.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Route {
    private final DoubleProperty distance;
    private final DoubleProperty duration;
    private final String geoJson;

    public Route(double distance, double duration, String geoJson) {
        this.distance = new SimpleDoubleProperty(distance);
        this.duration = new SimpleDoubleProperty(duration);
        this.geoJson = geoJson;
    }

    public double getDistance() {
        return distance.get();
    }

    public double getDuration() {
        return duration.get();
    }

    public String getGeoJson() {
        return geoJson;
    }

    public DoubleProperty distanceProperty() {return distance;}

    public DoubleProperty durationProperty() {return duration;}
}
