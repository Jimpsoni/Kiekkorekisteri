package com.example.harkkatyo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

public class SuodataController {
        @FXML
        private Button suodataTuloksia, peruutaSuodataTuloksia;

        @FXML
        public void suodataTulokset(ActionEvent event) {
            Stage stage = (Stage) peruutaSuodataTuloksia.getScene().getWindow();
            stage.close();
        }

        @FXML
        public void peruutaSuodataTulokset(ActionEvent event) {
            Stage stage = (Stage) peruutaSuodataTuloksia.getScene().getWindow();
            stage.close();
        }

        /**
         * Alustetaan controllerille sopivat arvot
         */
        public void alusta() {

        }


        /**
         * Tekee taulukon kaikista numeerisista pareista
         * @return taulukon, jossa kaikki numeeriset parit
         */
        public int[][] getNumericValues() {
                    int[][] arvot = new int[7][2];

                    arvot[0] = tarkasta(minPaino.getText(), maxPaino.getText());
                    arvot[1] = tarkasta(minVuosi.getText(), maxVuosi.getText());
                    arvot[2] = tarkasta(minNopeus.getText(), maxNopeus.getText());
                    arvot[3] = tarkasta(minLiito.getText(), maxLiito.getText());
                    arvot[4] = tarkasta(minVakaus.getText(), maxVakaus.getText());
                    arvot[5] = tarkasta(minFeidi.getText(), maxFeidi.getText());
                    arvot[6] = tarkasta(minKunto.getText(), maxKunto.getText());

                    System.out.println(Arrays.deepToString(arvot));
                    return arvot;

        }


    /**
     * Palauttaa kaikki merkkijono suodattimet
     * @return taulukon, jossa suodattimet on
     */
    public String[] getStringValues() {
            return new String[] {suodataMuovi.getValue(), suodataVari.getValue()};
        }


        /**
         * Arvio onko saatu merkkijono luku, jos ei niin annetaan arvoksi INT datatyypin minimi tai maksimi riippuen
         * kumpaa arvioidaan
         *
         * @param min minimiarvo, joka halutaan suodattaa
         * @param max maksimiarvo, joka halutaan suodattaa
         * @return taulukon, jossa on minimi ja maksimi kokonaislukuina
         */
        public int[] tarkasta(String min, String max) {
                int[] pari = new int[2];

                if (min == null || !onkoNumero(min)) pari[0] = Integer.MIN_VALUE;
                else pari[0] = Integer.parseInt(min);

                if (min == null || !onkoNumero(max)) pari[1] = Integer.MAX_VALUE;
                else pari[1] = Integer.parseInt(min);

                return pari;
        }


        /**
         * Palauttaa true tai false että saadaanko merkkijonosta parsittua kokonaisluku
         *
         * @param merkkijono merkkijono, jota tarkastellaan
         * @return boolean
         */
        public boolean onkoNumero(String merkkijono) {
                try {
                    Integer.parseInt(merkkijono);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }


        @FXML
        private TextField maxFeidi, maxKunto, maxLiito, maxNopeus, maxPaino, maxVakaus, maxVuosi, minFeidi, minKunto,
                          minLiito, minNopeus, minPaino, minVakaus, minVuosi;


        @FXML
        private ChoiceBox<String> suodataMuovi, suodataVari;
}
