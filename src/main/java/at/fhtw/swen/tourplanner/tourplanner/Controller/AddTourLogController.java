package at.fhtw.swen.tourplanner.tourplanner.Controller;

import at.fhtw.swen.tourplanner.tourplanner.viewModel.AddTourViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class AddTourLogController {

    @Autowired
    private AddTourViewModel viewModel;

    //Input form fields
    @FXML private TextField date;
    @FXML private TextField time;
    @FXML private TextField comment;
    @FXML private TextField difficulty; //TODO integer parsing
    @FXML private TextField distance; //TODO Distance??
    @FXML private TextField timeTaken; // TODO Time taken??
    @FXML private TextField rating; //TODO integer parsing

    @FXML
    public void initialize() {
        //Binding inputs to viewModel variables
    }

    @FXML
    public void handleSave(){
        viewModel.saveTour();
        ((Stage) comment.getScene().getWindow()).close();
    }
}
