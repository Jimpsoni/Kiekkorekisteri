package com.example.harkkatyo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValmistajaJaMallit {

    String path;
    private final List<ValmistajaJaMalli> lista = new ArrayList<ValmistajaJaMalli>();


    public static void main(String[] args) {
        ValmistajaJaMallit vm = new ValmistajaJaMallit();
        String[] haettava = new String[] {"Discmania", "Logic", "3", "3", "0", "2"};
        System.out.println(vm.onkoOlemassa(haettava));
    }


    /**
     * Oletus alustaja
     */
    public ValmistajaJaMallit() {
        path = "./valmistajajamalli.dat";
        lataaTiedot();
    }


    /**
     * Käytetään esim. testaamiseen
     *
     * @param path mistä polusta tiedosto löytyy
     */
    public ValmistajaJaMallit(String path) {
        this.path = path;
        lataaTiedot();
    }


    /**
     * Tarkistaa onko kyseisillä arvoilla olemassa jo kiekkoa, jos ei ole
     * kirjoittaa uuden instanssin, jos on niin hakee sen ID:n
     *
     * @return ID, jossa arvot ovat
     */
    public int onkoOlemassa(String[] tiedot) {
        // Käydään läpi kaikki kiekot
        for (ValmistajaJaMalli vm: lista) {
            String[] vmTiedot = vm.getInformation();
            if (
                vmTiedot[0].equals(tiedot[0]) &&
                vmTiedot[1].equals(tiedot[1]) &&
                vmTiedot[2].equals(tiedot[2]) &&
                vmTiedot[3].equals(tiedot[3]) &&
                vmTiedot[4].equals(tiedot[4]) &&
                vmTiedot[5].equals(tiedot[5])
            ) return vm.getID();
        }

        return lisaaUusi(tiedot);
    }


    /**
     * Lisää uuden valmistajaJaMalli objektin listaan
     * @param palat rivi joka lisätään
     */
    public int lisaaUusi(String[] palat) {
        int ID = lista.size() + 1;
        ValmistajaJaMalli uusi = new ValmistajaJaMalli();
        uusi.parseWithOutID(ID, palat);

        lista.add(uusi);
        return ID;
    }


    /**
     * Lataa tiedot tiedostosta, tekee uusia ValmistajaJaMalli objekteja ja
     * lisää ne listaan
     */
    public void lataaTiedot() {
        // Avataan tiedostot ja käydään läpi polun määrittämä tekstitiedosto
        try (Scanner fi = new Scanner(new FileInputStream(path))) {
            // Skipataan otsikko rivi
            fi.nextLine();

            while (fi.hasNext()) {
                // Luetaan ja pilkotaan rivi
                String[] s = fi.nextLine().split("\\|");
                ValmistajaJaMalli vm = new ValmistajaJaMalli();

                // Laitetaan tiedot objektiin
                vm.parse(s);

                // Lisätään se listaan
                lista.add(vm);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Jotain sattui: " + ex);
        }
    }


    /**
     * Kirjoittaa listan tiedostoon
     */
    public void kirjoitaTaulukkoTiedostoon() {
        try (FileWriter w = new FileWriter(path, false)) {
        } catch (IOException ex) {
            System.out.println("Ei löytynyt: " + ex);
        }


        try (PrintStream fo = new PrintStream(new FileOutputStream(path, true))) {
            fo.print("ID|valmistaja|malli|nopeus|liito|vakaus|feidi\n");
            for (ValmistajaJaMalli vm: lista) {
                fo.printf(vm.getFileString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param ID ID jota halutaan hakea malleista
     * @return Se muovi, jota ID:llä haetaan ja jos ei löydy, niin tyhjä merkkijono
     */
    public ValmistajaJaMalli getByID(int ID) {
        for (ValmistajaJaMalli vm: lista) {
            if (vm.getID() == ID) return vm;
        }
        return new ValmistajaJaMalli();
    }
}
