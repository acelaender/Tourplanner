package at.fhtw.swen.tourplanner.tourplanner.Service;

import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourEntryModel;
import at.fhtw.swen.tourplanner.tourplanner.dal.repository.TourRepository;
import at.fhtw.swen.tourplanner.tourplanner.model.Location;
import at.fhtw.swen.tourplanner.tourplanner.model.Route;
import at.fhtw.swen.tourplanner.tourplanner.model.TourEntry;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Component
public class TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public TourEntryModel save(TourEntry tourEntry) {
        return tourRepository.save(tourToModelMapper(tourEntry));
    }

    public List<TourEntry> findAll() {
        List<TourEntryModel> tourEntryModels = tourRepository.findAll();
        List<TourEntry> tourEntryList = new ArrayList<>();
        for (int i = 0; i < tourEntryModels.toArray().length; i++) {
            tourEntryList.add(modelToTourMapper(tourEntryModels.get(i)));
        }
        return tourEntryList;
    }

    public TourEntryModel findTourEntryModelById(int id){
        return tourRepository.findById(id).orElse(null);
    }

    public void delete(TourEntry tourEntry) {
        tourRepository.deleteById(tourEntry.getId());
    }

    public TourEntryModel overwrite(TourEntry tourEntry) {
        TourEntryModel tour = tourToModelMapper(tourEntry);
        tour.setId(tourEntry.getId());
        return tourRepository.save(tour);
    }

    public TourEntryModel tourToModelMapper(TourEntry tourEntry) {
        return new TourEntryModel(tourEntry.getName(), tourEntry.getDescription(), tourEntry.getFrom().getName(), tourEntry.getFrom().getLon(), tourEntry.getFrom().getLat(), tourEntry.getTo().getName(), tourEntry.getTo().getLon(), tourEntry.getTo().getLat(), tourEntry.getTransportType(), tourEntry.getRoute().getDuration(), tourEntry.getRoute().getDistance(), tourEntry.getRoute().getGeoJson(), tourEntry.getRouteInfo());
    }

    public TourEntry modelToTourMapper(TourEntryModel tourEntryModel) {
        TourEntry result = new TourEntry();
        result.setId(tourEntryModel.getId());
        result.setName(tourEntryModel.getName());
        result.setDescription(tourEntryModel.getDescription());
        result.setFrom(new Location(tourEntryModel.getStartLocationName(), tourEntryModel.getStartLocationLon(), tourEntryModel.getStartLocationLat()));
        result.setTo(new Location(tourEntryModel.getEndLocationName(), tourEntryModel.getEndLocationLon(), tourEntryModel.getEndLocationLat()));
        result.setTransportType(tourEntryModel.getTransportType());
        result.setRoute(new Route(tourEntryModel.getDistance(), tourEntryModel.getDuration(), tourEntryModel.getRouteJson()));
        result.setRouteInfo(tourEntryModel.getRouteInfo());

        return result;
    }


}
