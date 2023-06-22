package org.openjfx.javafxmavenarchetypes;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.openjfx.javafxmavenarchetypes.model.Bibliotheque;
import org.openjfx.javafxmavenarchetypes.controller.FormController;

import java.io.IOException;

public class HelloApplication extends Application {

    /** private ObservableList<Bibliotheque.Livre> listData = FXCollections.observableArrayList();**/
    /**
     *Permet d'avoir un executable
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Biblio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 870, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**  public ObservableList<Bibliotheque.Livre> getListData() {
     return listData;
     }
     **/

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}