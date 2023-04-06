package com.example.harkkatyo;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class PaaikkunaController {

    private final Kiekkorekisteri kiekkorekisteri = new Kiekkorekisteri();

    @FXML
    private Button lisaa, muokkaa, nayta, poista, suodata;
    @FXML
    private ListView<Kiekko> hakutulokset;

    @FXML
    private TextField hakukentta;

    private Kiekko valittu;


    /**
     * Ajetaan, kun käynnistetään ohjelma
     */
    @FXML
    private void initialize() {
        // Muutetaan tietolaatikot vain-luku tilaan

        tietoErikoisera.setEditable(false);
        tietoFeidi.setEditable(false);
        tietoVari.setEditable(false);
        tietoPaino.setEditable(false);
        tietoVakaus.setEditable(false);
        tietoMuovi.setEditable(false);
        tietoTussit.setEditable(false);
        tietoLiito.setEditable(false);
        tietoKunto.setEditable(false);
        tietoVuosi.setEditable(false);
        tietoNopeus.setEditable(false);


        kiekkorekisteri.annaKiekoilleOtsikko();  // Ajetaan tämä, niin jokainen kiekko saa tarvittavat arvot

        // Ladataan kiekot valikkoon
        for (Kiekko kiekko : kiekkorekisteri.getKiekot()) {
            hakutulokset.getItems().add(kiekko);
        }

        // Tehdään kuuntelija, joka pitää kirjaa mitä kiekkoa tarkastellaan
        hakutulokset.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Kiekko>() {
            @Override
            public void changed(ObservableValue<? extends Kiekko> observableValue, Kiekko kiekko, Kiekko t1) {
                valittu = hakutulokset.getSelectionModel().getSelectedItem();
            }
        });
    }


    /**
     * Ajetaan, kun sammutetaan ohjelma
     */
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }


    /**
     * Päivittää tiedot pääohjelmaan
     */
    public void refresh() {
        hakutulokset.getItems().clear();
        for (Kiekko kiekko : kiekkorekisteri.getKiekot()) {
            hakutulokset.getItems().addAll(kiekko);
        }
    }


    /**
     * Kutsuu kiekkorekisterin tallenna metodia
     */
    public void tallenna() {
        kiekkorekisteri.tallenna();
    }


    @FXML
    public void handleSuodataClick(ActionEvent event) throws IOException {
        Stage ikkuna = new Stage();

        // Estetään pääsy pääikkunaan
        ikkuna.initModality(Modality.APPLICATION_MODAL);
        ikkuna.setTitle("Suodata tuloksia");
        ikkuna.setMinWidth(250);

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("suodata.fxml"));
        Scene scene = new Scene(loader.load());

        loader.setRoot(this);

        SuodataController suodatacontroller = loader.getController();
        suodatacontroller.alusta();

        ikkuna.setScene(scene);
        ikkuna.showAndWait();

        //TODO hae suodattimet
    }

    //===========================================================================================================
                                    // POISTAMINEN


    /**
     * Nostaa hälytyksen ja jos vielä painetaan että ollaan varmoja poistosta niin
     * poistetaan ID:llä kiekko
     * @param event ei käytössä
     *
     */
    @FXML
    public void handlePoistaClick(ActionEvent event) throws IOException {
        int ID = valittu.getID();

        // Kysytään käyttäjältä, haluaako varmasti poistaa
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Haluatko varmasti poistaa kiekon ID:llä " + valittu.getID() + "?");
        Optional<ButtonType> valinta = alert.showAndWait();

        // Jos haluaa
        if (valinta.get() == ButtonType.OK) {
            kiekkorekisteri.poistaKiekko(ID);
            refresh();
        }
    }


    /**
     * @param event ei käytössä
     * @throws IOException
     *
     * Päivittää tiedot ohjelmaan
     *
     */
    @FXML
    public void handleNaytaClick(ActionEvent event) throws IOException {
        if (valittu == null) {
            Ikkuna.display("toteutus.fxml", "Huom!");
            return;
        }

        show(valittu);
    }


    public void show(Kiekko kiekko) {
        String[] tiedot = kiekkorekisteri.naytaKiekko(valittu);

        // TODO täytyy olla parempikin tapa tehä
        tietoOtsikko.setText(valittu.toString());
        tietoVari.setText(tiedot[0]);
        tietoVuosi.setText(tiedot[1]);
        tietoPaino.setText(tiedot[2]);
        tietoNopeus.setText(tiedot[3]);
        tietoLiito.setText(tiedot[4]);
        tietoVakaus.setText(tiedot[5]);
        tietoFeidi.setText(tiedot[6]);
        tietoMuovi.setText(tiedot[7]);
        tietoKunto.setText(tiedot[8]);
        tietoTussit.setText(tiedot[9]);
        tietoErikoisera.setText(tiedot[10]);
    }

    /**
     * Ottaa hakukenttä tietokentästä sinne kirjoitetun
     * tekstin ja tekee siitä säännöllisen lausekkeen
     * ja käyttää kiekkorekisterin readfile metodia
     *
     * @param event ei käytössä
     */
    @FXML
    public void hakeminen(KeyEvent event) {
        // Muotoillaan regex merkkijono
        String haku = "^" + hakukentta.getText() +  "[a-z]*";
        Kiekko[] tulokset = kiekkorekisteri.suodata(haku);

        // Poistetaan kaikki edelliset tulokset ja lisätään uudet
        hakutulokset.getItems().clear();

        for (Kiekko kiekko: tulokset) {
            if (kiekko == null) return;
            hakutulokset.getItems().add(kiekko);
        }
    }


    //===========================================================================================================
                                    // TIETONÄYTTÖ

    @FXML
    private TextField tietoErikoisera, tietoFeidi, tietoKunto, tietoLiito, tietoMuovi, tietoPaino, tietoTussit, tietoVakaus,
            tietoVari, tietoVuosi, tietoNopeus;
    @FXML
    private Label tietoOtsikko;

    //===========================================================================================================
                                // DATAN MUOKKAAMINEN
    @FXML
    public void handleMuokkaaClick(ActionEvent event) throws IOException {
        Stage ikkuna = new Stage();

        // Estetään pääsy pääikkunaan
        ikkuna.initModality(Modality.APPLICATION_MODAL);
        ikkuna.setTitle("Muokkaa");
        ikkuna.setMinWidth(250);

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("muokkaa.fxml"));
        Scene scene = new Scene(loader.load());

        loader.setRoot(this);

        MuokkaaController muokkaacontroller = loader.getController();
        muokkaacontroller.alusta(valittu, kiekkorekisteri);

        ikkuna.setScene(scene);
        ikkuna.showAndWait();
        if (muokkaacontroller.getTallenetaan()) {
            kiekkorekisteri.muokkaaKiekkoa(valittu, muokkaacontroller.getTiedot());
        }

        show(valittu);

    }


    //===========================================================================================================
                                // DATAN LISÄÄMINEN

    @FXML
    public void handleLisaaClick(ActionEvent event) throws IOException {
        Stage ikkuna = new Stage();

        // Estetään pääsy pääikkunaan
        ikkuna.initModality(Modality.APPLICATION_MODAL);
        ikkuna.setTitle("Lisää uusi kiekko");
        ikkuna.setMinWidth(250);

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("lisaa.fxml"));
        Scene scene = new Scene(loader.load());

        loader.setRoot(this);

        LisaaController lisaaController = loader.getController();
        lisaaController.alusta(kiekkorekisteri);

        ikkuna.setScene(scene);
        ikkuna.showAndWait();

        if (lisaaController.getTallennetaan()) {
            String[] tiedot = lisaaController.getData();
            kiekkorekisteri.lisaaUusiKiekko(tiedot);
            refresh();
        }
    }

}