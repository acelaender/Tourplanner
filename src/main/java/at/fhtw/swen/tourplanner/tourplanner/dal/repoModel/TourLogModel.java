package at.fhtw.swen.tourplanner.tourplanner.dal.repoModel;

import com.sun.istack.NotNull;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
public class TourLogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "tour_id", nullable = false, updatable = false)
    private TourEntryModel tour;

    @NotNull
    private LocalDate date;
    @NotNull
    private String comment;
    @NotNull
    private int rating;
    @NotNull
    private int difficulty;
    @NotNull
    private int distance;
    private int durationHours;
    @NotNull
    private int durationMinutes;

    public TourLogModel(TourEntryModel tour, LocalDate date, String comment, int rating, int difficulty, int distance, int durationHours, int durationMinutes) {
        this.tour = tour;
        this.date = date;
        this.comment = comment;
        this.rating = rating;
        this.difficulty = difficulty;
        this.distance = distance;
        this.durationHours = durationHours;
        this.durationMinutes = durationMinutes;
    }

    public TourLogModel(){}

    public void setId(int id){
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
