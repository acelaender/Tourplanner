package at.fhtw.swen.tourplanner.tourplanner.viewModel;

import at.fhtw.swen.tourplanner.tourplanner.Service.TourLogService;
import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourEntryModel;
import at.fhtw.swen.tourplanner.tourplanner.dal.repository.TourLogRepositoryDemo;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AddTourLogViewModel {
    private TourLog tourLog = new TourLog();
    private static final Logger logger = LogManager.getLogger(AddTourLogViewModel.class);
    private final TourLogRepositoryDemo repository = new TourLogRepositoryDemo();
    private final TourLogService tourLogService;
    private TourEntryModel tour;

    private TourLog editLog;

    public AddTourLogViewModel(TourLogService tourLogService){
        this.tourLogService = tourLogService;
    }

    public void setEditTour(TourLog tourlog){
        this.editLog = tourlog;
        this.tourLog = tourlog;
    }

    public void saveTourLog(){

        if(editLog == null) {
            tourLogService.save(tourLog, tour);
            logger.info("Saved tour log: " + tourLog.toString());
        } else {
            logger.info("overwriting tour log: " + tourLog.toString());
            tourLogService.overwrite(tourLog, tour);
        }

    }

    public TourEntryModel getTour() {
        return tour;
    }

    public void setTour(TourEntryModel tour) {
        this.tour = tour;
    }


    public ObjectProperty<LocalDate> dateProperty() {return tourLog.dateProperty();}
    public StringProperty commentProperty(){return tourLog.commentProperty();}
    public IntegerProperty distanceProperty(){return tourLog.distanceProperty();}
    public  IntegerProperty timeHoursProperty(){return tourLog.timeHoursProperty();}
    public IntegerProperty timeMinutesProperty(){return tourLog.timeMinutesProperty();}
    public IntegerProperty ratingProperty(){return tourLog.ratingProperty();}
    public IntegerProperty difficultyProperty(){ return tourLog.difficultyProperty(); }
}
