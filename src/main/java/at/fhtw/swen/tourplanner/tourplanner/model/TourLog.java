package at.fhtw.swen.tourplanner.tourplanner.model;

import jakarta.persistence.Entity;
import javafx.beans.property.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class TourLog {
    private int tourId; //badly named, this is log id
    private final ObjectProperty<LocalDate> date;
    private final StringProperty comment;
    private final IntegerProperty difficulty;
    private final IntegerProperty distance;
    private final IntegerProperty timeHours;
    private final IntegerProperty timeMinutes;
    private final IntegerProperty rating;

    public TourLog(int tourId, LocalDate date, String comment, int difficulty, int distance, int timeHours, int timeMinutes, int rating) {
        this.tourId = tourId;
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.comment = new SimpleStringProperty(comment);
        this.difficulty = new SimpleIntegerProperty(difficulty);
        this.distance = new SimpleIntegerProperty(distance);
        this.timeHours = new SimpleIntegerProperty(timeHours);
        this.timeMinutes = new SimpleIntegerProperty(timeMinutes);
        this.rating = new SimpleIntegerProperty(rating);
    }

    public TourLog() {
        this.tourId = 0;
        this.date = new SimpleObjectProperty<LocalDate>();
        this.comment = new SimpleStringProperty("");
        this.difficulty = new SimpleIntegerProperty(0);
        this.distance = new SimpleIntegerProperty(0);
        this.timeHours = new SimpleIntegerProperty(0);
        this.timeMinutes = new SimpleIntegerProperty(0);
        this.rating = new SimpleIntegerProperty(0);
    }

    /*public String getDate() {
        return date.get();
    }
     */

    public int getTourId() {
        return tourId;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public IntegerProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty.set(difficulty);
    }

    public int getDistance() {
        return distance.get();
    }

    public IntegerProperty distanceProperty() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance.set(distance);
    }

    public int getTimeHours() {
        return timeHours.get();
    }

    public IntegerProperty timeHoursProperty() {
        return timeHours;
    }

    public void setTimeHours(int time) {
        this.timeHours.set(time);
    }

    public int getTimeMinutes() {
        return timeMinutes.get();
    }

    public IntegerProperty timeMinutesProperty() {
        return timeMinutes;
    }

    public void setTimeMinutes(int time) {
        this.timeMinutes.set(time);
    }

    public int getRating() {
        return rating.get();
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    @Override
    public String toString(){
        return "Tour " + this.date + " " + this.comment +";";
    }
}
