package at.fhtw.swen.tourplanner.tourplanner.dal.repository;

import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;

import java.util.ArrayList;
import java.util.List;

public class TourRepositoryDemo {
    private static final List<TourEntry> tourList = new ArrayList<>();

    public void saveTour(TourEntry tour){
        tourList.add(tour);
    }

    public void deleteTour(TourEntry tour){
        tourList.remove(tour);
    }

    public List<TourEntry> getTourList(){
        return new ArrayList<>(tourList);
    }
}
