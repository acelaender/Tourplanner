package at.fhtw.swen.tourplanner.tourplanner.dal.repository;

import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;

import java.util.ArrayList;
import java.util.List;

public class TourLogRepositoryDemo {
    private static final List<TourLog> tourLogList = new ArrayList<>();

    public void saveTourLog(TourLog tourLog){
        tourLogList.add(tourLog);
    }

    public void deleteTourLog(TourEntry tour){
        tourLogList.remove(tour);
    }

    public List<TourLog> getTourLogList(){
        return new ArrayList<>(tourLogList);
    }
}
