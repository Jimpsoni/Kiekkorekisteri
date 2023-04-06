package com.example.harkkatyo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    FXMLLoader loader;


    /**
     * Luo uuden ikkunan FXML tiedostosta
     *
     * @param stage ei käytössä
     * @throws IOException Jos ei löydykkään tiedostoa
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        loader = fxmlLoader;
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Kiekkorekisteri");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Kutsutaan, kun ohjelma suljetaam
     */
    @Override
    public void stop(){
        PaaikkunaController controller = loader.getController();
        controller.tallenna();
    }


    /**
     * Käynnistää itse ohjelman
     */
    public static void main(String[] args) {
        launch();
    }
}