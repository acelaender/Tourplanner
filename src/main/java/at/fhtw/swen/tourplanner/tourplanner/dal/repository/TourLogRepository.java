package at.fhtw.swen.tourplanner.tourplanner.dal.repository;


import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourEntryModel;
import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourLogModel;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourLogRepository extends JpaRepository<TourLogModel,Integer> {
    List<TourLogModel> findByTour(TourEntryModel tour);
}
