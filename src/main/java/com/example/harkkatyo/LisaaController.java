package com.example.harkkatyo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LisaaController {
    private PaaikkunaController controller;

    /**
     * Hoitaa "lisää" näytön ohjaamisen
     */
    public void alusta(Kiekkorekisteri kiekkorekisteri) {
        muovit = kiekkorekisteri.getMuovit();
        uusiVari.getItems().addAll(varit);
        uusiMuovi.getItems().addAll(muovit);
    }

    @FXML
    private ToggleGroup erikoisera, tussi;

    @FXML
    private RadioButton erikoiseraEi, erikoiseraJoo, tussiEi, tussiJoo;

    @FXML
    private Button peruutaLisaaNappi, lisaaNappi;

    @FXML
    private TextField Feidi, Kunto, Liito, Malli, Nopeus, Paino, Vakaus, Valmistaja, Vuosi;

    @FXML
    private ChoiceBox<String> uusiMuovi, uusiVari;
    private String[] muovit;
    private final String[] varit = {"Punainen", "keltainen", "Sininen", "Valkoinen"};
    private boolean tallennetaan;


    public String[] getData() {
        String tussiValinta = tussiJoo.isSelected() ? "Kyllä" : "Ei";
        String erikoiseraValinta = erikoiseraJoo.isSelected() ? "Kyllä" : "Ei";
        return new String[] {
                Valmistaja.getText(),   // 0
                Malli.getText(),        // 1
                Vuosi.getText(),        // 2
                Paino.getText(),        // 3
                Nopeus.getText(),       // 4
                Liito.getText(),        // 5
                Vakaus.getText(),       // 6
                Feidi.getText(),        // 7
                Kunto.getText(),        // 8
                tussiValinta,               // 9
                erikoiseraValinta,          // 10
                uusiMuovi.getValue(),       // 11
                uusiVari.getValue(),        // 12
        };
    }

    public boolean getTallennetaan() {
        return tallennetaan;
    }


    public void varoitusIkkuna(String teksti) {
        // Sanotaan käyttäjälle, ettei tätä arvoa voi antaa kiekolle
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(teksti);
        alert.showAndWait();
    }


    public boolean tarkastaTekstikenttaNumeerinen(int min, int max, TextField text) {
        // TODO mitä jos tekstikenttä on tyhjä?

        int arvo;
        try {
            arvo = Integer.parseInt(text.getText());
        } catch (NumberFormatException e) {
            text.setStyle("-fx-text-inner-color: red;");
            varoitusIkkuna("Antamasi arvo kentälle " + text.getId() + " ei ole kokonaisluku");
            return false;
        }

        if (arvo >= min && arvo <= max) {
            text.setStyle("-fx-text-inner-color: black;");
            return true;
        }

        text.setStyle("-fx-text-inner-color: red;");
        varoitusIkkuna("Antamasi arvo kentälle " + text.getId() + " ei ole välillä " + min + " ja " + max);
        return false;
    }


    public boolean tarkastaTekstikenttaMerkkijono(int max, TextField text) {
        if (text.getText().length() <= max) {
            text.setStyle("-fx-text-inner-color: black;");
            return true;
        }

        text.setStyle("-fx-text-inner-color: red;");
        varoitusIkkuna("Antamasi arvo kentälle " + text.getId() + " on yli sallitun merkkimäärän: " + max);
        return false;
    }


    @FXML
    void lisaaDataa(ActionEvent event) {

        // TODO mitä vittua tää on

        boolean valmistajaOK = tarkastaTekstikenttaMerkkijono(15, Valmistaja);
        boolean malliOK = tarkastaTekstikenttaMerkkijono(15, Malli);
        boolean vuosiOK = tarkastaTekstikenttaNumeerinen(1980, 2023, Vuosi);
        boolean painoOK = tarkastaTekstikenttaNumeerinen(130, 200, Paino);
        boolean nopeusOK = tarkastaTekstikenttaNumeerinen(1, 15, Nopeus);
        boolean liitoOK = tarkastaTekstikenttaNumeerinen(0, 7, Liito);
        boolean vakausOK = tarkastaTekstikenttaNumeerinen(-4, 2, Vakaus);
        boolean feidiOK = tarkastaTekstikenttaNumeerinen(0, 7, Feidi);
        boolean kuntoOK = tarkastaTekstikenttaNumeerinen(0, 10, Kunto);

        if (
            valmistajaOK && malliOK && vuosiOK && painoOK && nopeusOK && liitoOK && vakausOK && feidiOK && kuntoOK
        ) {
            String tussiValinta = tussiJoo.isSelected() ? "Kyllä" : "Ei";
            String erikoiseraValinta = erikoiseraJoo.isSelected() ? "Kyllä" : "Ei";

            String[] tiedot = {
                    Valmistaja.getText(),   // 0
                    Malli.getText(),        // 1
                    Vuosi.getText(),        // 2
                    Paino.getText(),        // 3
                    Nopeus.getText(),       // 4
                    Liito.getText(),        // 5
                    Vakaus.getText(),       // 6
                    Feidi.getText(),        // 7
                    Kunto.getText(),        // 8
                    tussiValinta,               // 9
                    erikoiseraValinta,          // 10
                    uusiMuovi.getValue(),       // 11
                    uusiVari.getValue(),        // 12
            };

            tallennetaan = true;
            // Lähetä
            Stage stage = (Stage) lisaaNappi.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void peruutaLisaaminen(ActionEvent event) {
        tallennetaan = false;
        // Jos käyttäjä peruuttaa lisäämisen
        Stage stage = (Stage) peruutaLisaaNappi.getScene().getWindow();
        stage.close();
    }

}
