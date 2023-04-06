package com.example.harkkatyo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Muovit {

    private final List<Muovi> lista = new ArrayList<>();
    private final String path;

    /**
     * Oletus alustaja
     */
    public Muovit() {
        path = "./Muovit.dat";
        lataaMuovit();
    }

    /**
     * Käytetään esim. testaamiseen
     * @param path mistä polusta tiedosto löytyy
     */
    public Muovit(String path) {
        this.path = path;
        lataaMuovit();
    }

    public String[] getMuovit() {
        String[] palautettava = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            palautettava[i] = lista.get(i).getMuovi();
        }
        return palautettava;
    }

    /**
     * Ladataan muovit taulukkoon
     */
    public void lataaMuovit() {
        // Avataan tiedostot ja käydään läpi kiekot.dat
        try (Scanner fi = new Scanner(new FileInputStream(path)))
        {
            // Skipataan otsikko rivi
            fi.nextLine();

            while ( fi.hasNext() ) {
                // Luetaan ja pilkotaan rivi
                String[] s = fi.nextLine().split("\\|");
                Muovi m = new Muovi();

                // Laitetaan tiedot muovi objektiin
                m.setID(Integer.parseInt(s[0]));
                m.setMuovi(s[1]);

                // Lisätään se listaan
                lista.add(m);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Jotain sattui: " + ex);
        }
    }


    /**
     * @param ID ID jota halutaan hakea muoveista
     * @return Se muovi, jota ID:llä haetaan ja jos ei löydy, niin tyhjä merkkijono
     */
    public String getByID(int ID) {
        for (Muovi muovi: lista) {
            if (muovi.getID() == ID) return muovi.getMuovi();
        }
        return "";
    }

    public int getByMuovi(String muovi) {
        for (Muovi m: lista) {
            if (m.getMuovi().equals(muovi)) return m.getID();
        }

        return -1;
    }
}
