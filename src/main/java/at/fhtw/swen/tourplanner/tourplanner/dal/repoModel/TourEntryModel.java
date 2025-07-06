package at.fhtw.swen.tourplanner.tourplanner.dal.repoModel;

import at.fhtw.swen.tourplanner.tourplanner.model.Location;
import at.fhtw.swen.tourplanner.tourplanner.model.Route;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

//import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TourEntryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    @NotBlank
    private String startLocationName;
    @NotNull
    @NotBlank
    private String startLocationLon;
    @NotNull
    @NotBlank
    private String startLocationLat;
    @NotNull
    @NotBlank
    private String endLocationName;
    @NotNull
    @NotBlank
    private String endLocationLon;
    @NotNull
    @NotBlank
    private String endLocationLat;
    @NotNull
    @NotBlank
    private String transportType;
    @NotNull
    private Double duration;
    @NotNull
    private Double distance;
    @NotNull
    @NotBlank
    @Lob
    @Column(columnDefinition = "CLOB")
    private String routeJson;
    @NotNull
    private String routeInfo;
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private final List<TourLogModel> tourLogModels = new ArrayList<>();

    public TourEntryModel(String name, String description, String startLocationName, String startLocationLon, String startLocationLat, String endLocationName, String endLocationLon, String endLocationLat, String transportType, Double duration, Double distance, String routeJson, String routeInfo) {
        this.name = name;
        this.description = description;
        this.startLocationName = startLocationName;
        this.startLocationLon = startLocationLon;
        this.startLocationLat = startLocationLat;
        this.endLocationName = endLocationName;
        this.endLocationLon = endLocationLon;
        this.endLocationLat = endLocationLat;
        this.transportType = transportType;
        this.duration = duration;
        this.distance = distance;
        this.routeJson = routeJson;
        this.routeInfo = routeInfo;
    }

    public TourEntryModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    public List<TourLogModel> getTourLogModels() {
        return tourLogModels;
    }

    public String getStartLocationName() {
        return startLocationName;
    }

    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    public String getStartLocationLon() {
        return startLocationLon;
    }

    public void setStartLocationLon(String startLocationLon) {
        this.startLocationLon = startLocationLon;
    }

    public String getStartLocationLat() {
        return startLocationLat;
    }

    public void setStartLocationLat(String startLocationLat) {
        this.startLocationLat = startLocationLat;
    }

    public String getEndLocationName() {
        return endLocationName;
    }

    public void setEndLocationName(String endLocationName) {
        this.endLocationName = endLocationName;
    }

    public String getEndLocationLon() {
        return endLocationLon;
    }

    public void setEndLocationLon(String endLocationLon) {
        this.endLocationLon = endLocationLon;
    }

    public String getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(String endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getRouteJson() {
        return routeJson;
    }

    public void setRouteJson(String routeJson) {
        this.routeJson = routeJson;
    }
}

