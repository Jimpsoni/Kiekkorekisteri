package com.example.harkkatyo;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Ikkuna {

    public static void display(String blueprint, String title) throws IOException {
        Stage ikkuna = new Stage();

        // Estetään pääsy pääikkunaan
        ikkuna.initModality(Modality.APPLICATION_MODAL);
        ikkuna.setTitle(title);
        ikkuna.setMinWidth(250);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(blueprint));
        Scene scene = new Scene(fxmlLoader.load());

        ikkuna.setScene(scene);
        ikkuna.showAndWait();

    }

}