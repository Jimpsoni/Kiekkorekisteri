package com.example.harkkatyo;
import java.util.Arrays;

public class Suodatin {
    private int[][] numeeriset;
    private String[] merkkijonot;

    public Suodatin() {}

    public boolean tarkastaKiekko(Kiekko kiekko) {
        System.out.println(Arrays.toString(numeeriset[0]));
        return true;
    }

    public void asetaNumeerisetSuodattimet(int[][] suodattimet) {
        numeeriset = suodattimet;
    }

    public void asetaValikkoSuodattimet(String[] merkkijonot) {

    }

}
