package at.fhtw.swen.tourplanner.tourplanner.Controller;

import at.fhtw.swen.tourplanner.tourplanner.dal.repoModel.TourEntryModel;
import at.fhtw.swen.tourplanner.tourplanner.model.TourLog;
import at.fhtw.swen.tourplanner.tourplanner.viewModel.AddTourLogViewModel;
import at.fhtw.swen.tourplanner.tourplanner.viewModel.AddTourViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddTourLogController {

    @Autowired
    private AddTourLogViewModel viewModel;

    //Input form fields
    @FXML private DatePicker datePicker;

    @FXML private TextField timeHours;
    @FXML private TextField timeMinutes;
    @FXML private TextField commentField;
    @FXML private ChoiceBox<Integer> difficultyField; //TODO integer parsing
    @FXML private TextField distanceField;
    @FXML private ChoiceBox<Integer> ratingField; //TODO integer parsing

    public AddTourLogController(AddTourLogViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setContext(TourLog tourLog) {
        //Setting context for Tour-Edit
        viewModel.setEditTour(tourLog);
        bind();
    }

    public void setTourModel(TourEntryModel tourEntryModel){
        viewModel.setTour(tourEntryModel);
    }

    @FXML
    public void initialize() {
        //Listeners for TimeInput fields - only Doubles allowed
        timeHours.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
                timeHours.setText(oldValue);
            }
        });
        timeMinutes.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
                timeMinutes.setText(oldValue);
            }
        });
        //Listener for Distance field
        distanceField.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
                distanceField.setText(oldValue);
            }
        });

        bind();
    }

    private void bind(){
        //Binding inputs to viewModel variables
        datePicker.valueProperty().bindBidirectional(viewModel.dateProperty());
        Bindings.bindBidirectional(timeHours.textProperty(), viewModel.timeHoursProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(timeMinutes.textProperty(), viewModel.timeMinutesProperty(), new NumberStringConverter());
        commentField.textProperty().bindBidirectional(viewModel.commentProperty());
        distanceField.textProperty().bindBidirectional(viewModel.distanceProperty(), new NumberStringConverter());
        ratingField.valueProperty().bindBidirectional(viewModel.ratingProperty().asObject());
        difficultyField.valueProperty().bindBidirectional(viewModel.difficultyProperty().asObject());
    }

    @FXML
    public void handleSave(){
        viewModel.saveTourLog();
        ((Stage) commentField.getScene().getWindow()).close();
    }
}
