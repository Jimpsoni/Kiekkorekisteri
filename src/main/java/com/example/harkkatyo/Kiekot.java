package com.example.harkkatyo;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Kiekot {

    String path;

    private final int MAXLKM = 10;
    private int LKM = 0;
    public Kiekko[] taulukko = new Kiekko[MAXLKM];


    /**
     * Kiekot luokka ylläpitää kiekot.dat tiedostoa. Se pystyy etsimään ja poistamaan ID:n perusteella.
     * Pystyy myös kirjoittamaan uusia kiekko luokan olioita
     */
    public Kiekot() {
        path = "kiekot.dat";
        lataaKiekot();
    }

    public Kiekot(String path) {
        this.path = path;
        lataaKiekot();
    }


    /**
     * Hakee maksimissaan MAXLKM määrä kiekkoja tiedostosta ja laittaa ne taulukkoon
     */
    public void lataaKiekot() {
        // Avataan tiedostot ja käydään läpi kiekot.dat
        try (Scanner fi = new Scanner(new FileInputStream(path))) {
            // Skipataan otsikko rivi
            fi.nextLine();

            while (fi.hasNext()) {
                // Luetaan ja pilkotaan rivi
                String[] s = fi.nextLine().split("\\|");
                Kiekko k = new Kiekko();
                // Laitetaan tiedot objektiin
                k.parse(s);

                // Lisätään se listaan
                if (LKM <= MAXLKM) { taulukko[LKM] = k; LKM++; }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Jotain sattui: " + ex);
        }
    }


    /**
     * Haetaan kiekko taulukko ja poistetaan siitä kaikki null arvot
     *
     * @return Taulukko, jossa kiekot on
     */
    public Kiekko[] getKiekot() {
        Kiekko[] palautus = new Kiekko[LKM];

        for (int i = 0; i < LKM; i++) {
            palautus[i] = taulukko[i];
        }
        return palautus;
    }


    /**
     * Tyhjentää taulukon
     */
    public void clearArray() {
        taulukko = new Kiekko[MAXLKM];
    }

    /**
     * Etsii kyseisen ID:n instanssin ja poistaa sen
     * tietokannasta
     *
     * @param ID ID Joka halutaan poistaa tiedostosta
     */
    public void deleteByID(int ID) {
        Kiekko[] t = getKiekot();
        clearArray();
        for (int i = 0; i < t.length; i++) {
            if (t[i].getID() == ID) continue;
            if (i > ID) { t[i].setID(t[i].getID() - 1); }
            taulukko[i] = t[i];
        }
        LKM--;
    }


    /**
     * Lisää uuden kiekon taulukkoon
     * @param s merkkijonotaulukko, jossa tarvittavat tiedot ovat
     */
    public Kiekko lisaaUusi(String[] s) {
        if (LKM >= MAXLKM) return new Kiekko();
        String data = LKM + 1 + "|" + String.join("|", s);
        Kiekko k = new Kiekko();
        k.parse(data.split("\\|"));

        taulukko[LKM] = k;
        LKM++;

        return k;
    }


    /**
     * Kirjoitetaan taulukko kiekot.dat tiedostoon
     */
    public void kirjoitaTaulukkoTiedostoon() {
        // Tyhjennetään ensin vanhat tiedot tiedostosta
        try (FileWriter w = new FileWriter(path, false)) {
        } catch (IOException ex) {
            System.out.println("Ei löytynyt: " + ex);
        }

        // Ja kirjoitetaan sitten uudet
        try (PrintStream fo = new PrintStream(new FileOutputStream(path, true))) {
            fo.print("ID|kiekko|väri|vuosi|paino|muovi|kunto|tussit|erikoisera\n");
            for (Kiekko kiekko: getKiekot()) {
                fo.printf(kiekko.getFileString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Pääohjelma testausta varten
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kiekot a = new Kiekot();
        String[] rivi = new String[] {"1", "Sininen", "2019", "200", "1", "3", "ei", "ei"};
        a.lisaaUusi(rivi);
        a.kirjoitaTaulukkoTiedostoon();
    }

}