package com.example.harkkatyo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MuokkaaController {

    @FXML
    private TextField muokkaaKunto, muokkaaPaino, muokkaaValmistusvuosi;
    @FXML
    private ChoiceBox<String> muokkaaMuovi, muokkaaVari;
    @FXML
    private Button muokkaaPeruutaNappi, muokkaaTallennaNappi;

    @FXML
    private ToggleGroup erikoisera, tussi;

    @FXML
    private RadioButton jalkiaEi, jalkiaOn, erikoisEi, erikoisOn;

    private Kiekko kiekko;

    private boolean tallennetaan;


    /**
     * Alustetaan perusarvot kentille
     * @param kiekko Saadaan kiekontiedot
     * @param kiekkorekisteri Voidaan hakea muovit yms...
     */
    public void alusta(Kiekko kiekko, Kiekkorekisteri kiekkorekisteri) {
        this.kiekko = kiekko;

        muokkaaKunto.setText(kiekko.getKunto());
        muokkaaPaino.setText(kiekko.getPaino());
        muokkaaValmistusvuosi.setText(kiekko.getValmistusvuosi());

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

    @FXML
    public void handleMuokkaaTallennaClick(ActionEvent event) {
        tallennetaan = true;
        Stage stage = (Stage) muokkaaTallennaNappi.getScene().getWindow();
        stage.close();
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

        return new String[] {muokkaaPaino.getText(), muokkaaValmistusvuosi.getText(), muokkaaKunto.getText(),
                tussiValinta, erikoiseraValinta, muokkaaMuovi.getValue(), muokkaaVari.getValue() };
    }

}
