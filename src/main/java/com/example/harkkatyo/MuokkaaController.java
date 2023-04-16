package com.example.harkkatyo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MuokkaaController {

    @FXML
    private TextField Kunto, Paino, Valmistusvuosi;
    @FXML
    private ChoiceBox<String> muokkaaMuovi, muokkaaVari;
    @FXML
    private Button muokkaaPeruutaNappi, muokkaaTallennaNappi;

    @FXML
    private ToggleGroup erikoisera, tussi;

    @FXML
    private RadioButton jalkiaEi, jalkiaOn, erikoisEi, erikoisOn;

    private boolean tallennetaan;


    /**
     * Alustetaan perusarvot kentille
     * @param kiekko Saadaan kiekontiedot
     * @param kiekkorekisteri Voidaan hakea muovit yms...
     */
    public void alusta(Kiekko kiekko, Kiekkorekisteri kiekkorekisteri) {

        Kunto.setText(String.valueOf(kiekko.getKunto()));
        Paino.setText(String.valueOf(kiekko.getPaino()));
        Valmistusvuosi.setText(String.valueOf(kiekko.getValmistusvuosi()));

        muokkaaMuovi.getItems().addAll(kiekkorekisteri.getMuovit());
        muokkaaVari.getItems().addAll("Punainen", "keltainen", "Sininen", "Valkoinen");
        muokkaaMuovi.setValue(kiekkorekisteri.getMuoviByID(kiekko.getMuoviID()));
        muokkaaVari.setValue(kiekko.getVari());

        jalkiaOn.setToggleGroup(tussi); jalkiaEi.setToggleGroup(tussi);
        erikoisEi.setToggleGroup(erikoisera); erikoisOn.setToggleGroup(erikoisera);

        if (kiekko.getTussit().equals("kyllä") ) {
            jalkiaOn.setSelected(true);
        } else { jalkiaEi.setSelected(true); }

        if (kiekko.getErikois().equals("kyllä")) {
            erikoisOn.setSelected(true);
        } else { erikoisEi.setSelected(true); }
    }


    public void varoitusIkkuna(String teksti) {
        // Sanotaan käyttäjälle, ettei tätä arvoa voi antaa kiekolle
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(teksti);
        alert.showAndWait();
    }


    public boolean tarkastaTekstikentta(int min, int max, TextField text) {
        //TODO mitä jos tekstikenttä on tyhjä?
        if (text.getText().isEmpty()) return true;

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

    @FXML
    public void handleMuokkaaTallennaClick(ActionEvent event) {
        boolean painoOK = tarkastaTekstikentta(130, 200, Paino);
        boolean kuntoOK = tarkastaTekstikentta(0, 10, Kunto);
        boolean valmistusvuosiOK = tarkastaTekstikentta(1980, 2023, Valmistusvuosi);

        if (painoOK && kuntoOK && valmistusvuosiOK) {
            tallennetaan = true;
            Stage stage = (Stage) muokkaaTallennaNappi.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void handleMuokkaaPeruutaClick(ActionEvent event) {
        tallennetaan = false;
        Stage stage = (Stage) muokkaaPeruutaNappi.getScene().getWindow();
        stage.close();
    }

    public boolean getTallenetaan() { return tallennetaan; }

    public String[] getTiedot() {
        String tussiValinta = jalkiaOn.isSelected() ? "Kyllä" : "Ei";
        String erikoiseraValinta = erikoisOn.isSelected() ? "Kyllä" : "Ei";

        return new String[] {Paino.getText(), Valmistusvuosi.getText(), Kunto.getText(),
                tussiValinta, erikoiseraValinta, muokkaaMuovi.getValue(), muokkaaVari.getValue() };
    }
}
