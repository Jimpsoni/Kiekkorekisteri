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
    private TextField uusiFeidi, uusiKunto, uusiLiito, uusiMalli, uusiNopeus, uusiPaino, uusiVakaus, uusiValmistaja, uusiVuosi;

    @FXML
    private ChoiceBox<String> uusiMuovi, uusiVari;
    private String[] muovit;
    private final String[] varit = {"Punainen", "keltainen", "Sininen", "Valkoinen"};
    private boolean tallennetaan;


    public String[] getData() {
        String tussiValinta = tussiJoo.isSelected() ? "Kyllä" : "Ei";
        String erikoiseraValinta = erikoiseraJoo.isSelected() ? "Kyllä" : "Ei";
        return new String[] {
                uusiValmistaja.getText(),   // 0
                uusiMalli.getText(),        // 1
                uusiVuosi.getText(),        // 2
                uusiPaino.getText(),        // 3
                uusiNopeus.getText(),       // 4
                uusiLiito.getText(),        // 5
                uusiVakaus.getText(),       // 6
                uusiFeidi.getText(),        // 7
                uusiKunto.getText(),        // 8
                tussiValinta,               // 9
                erikoiseraValinta,          // 10
                uusiMuovi.getValue(),       // 11
                uusiVari.getValue(),        // 12
        };
    }

    public boolean getTallennetaan() {
        return tallennetaan;
    }

    @FXML
    void lisaaDataa(ActionEvent event) {

        String tussiValinta = tussiJoo.isSelected() ? "Kyllä" : "Ei";
        String erikoiseraValinta = erikoiseraJoo.isSelected() ? "Kyllä" : "Ei";

        String[] tiedot = {
                uusiValmistaja.getText(),   // 0
                uusiMalli.getText(),        // 1
                uusiVuosi.getText(),        // 2
                uusiPaino.getText(),        // 3
                uusiNopeus.getText(),       // 4
                uusiLiito.getText(),        // 5
                uusiVakaus.getText(),       // 6
                uusiFeidi.getText(),        // 7
                uusiKunto.getText(),        // 8
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

    @FXML
    void peruutaLisaaminen(ActionEvent event) {
        tallennetaan = false;
        // Jos käyttäjä peruuttaa lisäämisen
        Stage stage = (Stage) peruutaLisaaNappi.getScene().getWindow();
        stage.close();
    }

}
