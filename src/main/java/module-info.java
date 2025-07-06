module at.fhtw.swen.tourplanner.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires org.apache.logging.log4j;
    requires java.net.http;
    requires org.json;
    requires spring.data.jpa;
   // requires java.persistence;
    requires jakarta.activation;
    requires com.sun.istack.runtime;
    requires org.apache.logging.log4j.core;
    requires spring.boot.autoconfigure;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.databind;


    opens at.fhtw.swen.tourplanner.tourplanner to javafx.fxml, spring.core, spring.beans, org.hibernate.orm.core;
    opens at.fhtw.swen.tourplanner.tourplanner.viewModel to spring.core, spring.beans;
    opens at.fhtw.swen.tourplanner.tourplanner.dal.repoModel to org.hibernate.orm.core, spring.core, com.fasterxml.jackson.databind;
    exports at.fhtw.swen.tourplanner.tourplanner;
    exports at.fhtw.swen.tourplanner.tourplanner.Service;
    exports at.fhtw.swen.tourplanner.tourplanner.Controller;
    exports at.fhtw.swen.tourplanner.tourplanner.dal.repoModel;
    opens at.fhtw.swen.tourplanner.tourplanner.Controller to javafx.fxml, spring.beans, spring.core;
}