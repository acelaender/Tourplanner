package at.fhtw.swen.tourplanner.tourplanner.Service;

import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourEntryModel;
import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourLogModel;
import at.fhtw.swen.tourplanner.tourplanner.dal.repository.TourLogRepository;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TourLogService {
    private final TourLogRepository tourLogRepository;

    @Autowired
    public TourLogService(TourLogRepository tourLogRepository) { this.tourLogRepository = tourLogRepository; }
    public TourLogModel save(TourLog tourLog, TourEntryModel tourEntryModel){
        return tourLogRepository.save(tourLogToModelMapper(tourLog, tourEntryModel));
    }

    public TourLogModel overwrite(TourLog tourLog, TourEntryModel tourEntryModel){
        TourLogModel tourLogModel = tourLogToModelMapper(tourLog, tourEntryModel);
        tourLogModel.setId(tourLog.getTourId());
        return  tourLogRepository.save(tourLogModel);
    }

    public List<TourLog> findAll(){
        List<TourLogModel> tourLogModelList = tourLogRepository.findAll();
        List<TourLog> tourLogList = new ArrayList<>();
        for (int i = 0; i < tourLogModelList.toArray().length; i++) {
            tourLogList.add(tourLogModelToTourLogMapper(tourLogModelList.get(i)));
        }
        return tourLogList;
    }

    public List<TourLog> findAllByTourEntryModel(TourEntryModel tourEntryModel){
        List<TourLogModel> tourLogModelList = tourLogRepository.findByTour(tourEntryModel);
        List<TourLog> tourLogList = new ArrayList<>();
        for (int i = 0; i < tourLogModelList.toArray().length; i++) {
            tourLogList.add(tourLogModelToTourLogMapper(tourLogModelList.get(i)));
        }
        return tourLogList;
    }

    public void delete(TourLog tourLog){
        tourLogRepository.deleteById(tourLog.getTourId());
    }


    public TourLogModel tourLogToModelMapper(TourLog tourLog, TourEntryModel tourEntryModel){
        return new TourLogModel(tourEntryModel, tourLog.dateProperty().get(), tourLog.getComment(), tourLog.getRating(), tourLog.getDifficulty(), tourLog.getDistance(), tourLog.getTimeHours(), tourLog.getTimeMinutes());
    }

    public TourLog tourLogModelToTourLogMapper(TourLogModel tourLogModel){
        return new TourLog(tourLogModel.getId(), tourLogModel.getDate(), tourLogModel.getComment(), tourLogModel.getDifficulty(), tourLogModel.getDistance(), tourLogModel.getDurationHours(), tourLogModel.getDurationMinutes(), tourLogModel.getRating());
    }
}
