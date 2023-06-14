package org.openjfx.javafxmavenarchetypes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

   /** private ObservableList<Bibliotheque.Livre> listData = FXCollections.observableArrayList();**/
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Biblio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 870, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
       //colTitre.setCellValueFactory(new PropertyValueFactory<Bibliotheque.Livre,String>("titreText"));
    }

  /**  public ObservableList<Bibliotheque.Livre> getListData() {
        return listData;
    }
   **/

    public static void main(String[] args) {
        launch();
    }
}