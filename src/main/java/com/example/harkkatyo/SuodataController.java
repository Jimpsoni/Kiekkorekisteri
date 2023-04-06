package com.example.harkkatyo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuodataController {
        @FXML
        private Button suodataTuloksia, peruutaSuodataTuloksia;

        @FXML
        public void suodataTulokset(ActionEvent event) throws IOException {
                // TODO
                Ikkuna.display("toteutus.fxml", "Huom!");
        }

        @FXML
        public void peruutaSuodataTulokset(ActionEvent event) throws IOException {
                Stage stage = (Stage) peruutaSuodataTuloksia.getScene().getWindow();
                stage.close();
        }

        /**
         * Alustetaan controllerille sopivat arvot
         */
        public void alusta() {

        }
}
