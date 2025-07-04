package at.fhtw.swen.tourplanner.tourplanner;

import at.fhtw.swen.tourplanner.tourplanner.Controller.MainController;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Objects;

public class MainApplication extends Application {

    private static AnnotationConfigApplicationContext context;
    private static final Logger logger = LogManager.getLogger(MainApplication.class);
    private static HostServices hostServices;

    @Override
    public void start(Stage stage) throws Exception{
        showStage(stage);
        hostServices = getHostServices();
    }

    public static Parent showStage(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        fxmlLoader.setControllerFactory(Controller -> context.getBean(MainController.class));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Tour Planner");
        primaryStage.setScene(new Scene(root, 1000, 800));
        logger.info("Application initialized, starting window");
        primaryStage.show();
        return root;
    }

    public static void initializeApp(){
        context = new AnnotationConfigApplicationContext();
        context.register(IoCContainerConfig.class);
        context.refresh();
    }


    public static void main(String[] args){
        initializeApp();
        launch();
    }

    public static void showMap() {
        URL url = MainApplication.class.getResource("/leaflet.html");
        System.out.println(url);
        hostServices.showDocument(Objects.requireNonNull(url).toExternalForm());
    }


}
