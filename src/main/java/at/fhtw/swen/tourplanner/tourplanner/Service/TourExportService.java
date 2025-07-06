package at.fhtw.swen.tourplanner.tourplanner.Service;

import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourEntryModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Component
@Service
public class TourExportService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TourExportService() {}

    public void exportTourToFile(TourEntryModel tour, File file) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, tour);
    }
}
