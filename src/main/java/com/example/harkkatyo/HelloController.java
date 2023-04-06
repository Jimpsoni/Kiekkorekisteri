package com.example.harkkatyo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloController {
    public Button PoistaNappi;
    @FXML
    private Button PeruutaPoistaNappi;




    /*

    SUODATUS


     */
    @FXML
    private Button suodataTuloksia;

    @FXML
    private Button peruutaSuodataTuloksia;

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


    /*

    POISTA

     */



    @FXML
    public void poistaData(ActionEvent event) throws IOException {
        Stage stage = (Stage) PoistaNappi.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void peruutaPoisto(ActionEvent event) throws IOException {
        Stage stage = (Stage) PeruutaPoistaNappi.getScene().getWindow();
        stage.close();
    }


    /*

    MUOKKAAMINEN

     */
    @FXML
    private Button muokkaaPeruutaNappi;

    @FXML
    private Button muokkaaTallennaNappi;

    @FXML
    public void handleMuokkaaTallennaClick(ActionEvent event) throws IOException {
        Ikkuna.display("toteutus.fxml", "Huom!");
    }

    @FXML
    public void handleMuokkaaPeruutaClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) muokkaaPeruutaNappi.getScene().getWindow();
        stage.close();
    }


    /*

    EI VIELÄ TOTEUTETTU RUUTU

     */
    @FXML
    private Button selvaNappi;

    @FXML
    public void handleClick(ActionEvent event) {
        Stage stage = (Stage) selvaNappi.getScene().getWindow();
        stage.close();
    }
}