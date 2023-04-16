package com.example.harkkatyo;

public class Suodatin {
    private int[][] numeeriset;
    private String[] merkkijonot;
    private boolean booleanit;

    public Suodatin() {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        numeeriset = new int[][] {
                new int[] {min, max},
                new int[] {min, max},
                new int[] {min, max},
                new int[] {min, max},
                new int[] {min, max},
                new int[] {min, max},
                new int[] {min, max},
        };

        merkkijonot = new String[] {null, null};
    }

    public boolean tarkastaKiekko(Kiekko kiekko) {
        if (numeeriset[0][0] > kiekko.getPaino() || numeeriset[0][1] < kiekko.getPaino()) return false;
        if (numeeriset[1][0] > kiekko.getValmistusvuosi() || numeeriset[1][1] < kiekko.getValmistusvuosi()) return false;
        return true;
    }

    public boolean tarkastaValmistajaJaMalli(int[] lentoarvot) {
        // Nopeus
        if (numeeriset[2][0] > lentoarvot[0] || numeeriset[2][1] < lentoarvot[0]) return false;
        if (numeeriset[3][0] > lentoarvot[1] || numeeriset[3][1] < lentoarvot[1]) return false;
        if (numeeriset[4][0] > lentoarvot[2] || numeeriset[4][1] < lentoarvot[2]) return false;
        if (numeeriset[5][0] > lentoarvot[3] || numeeriset[5][1] < lentoarvot[3]) return false;
        return true;
    }

    public boolean tarkastaMuovi(String muovi) {
        if (merkkijonot[0] == null) return true;
        return muovi.equals(merkkijonot[0]);
    }

    public void asetaNumeerisetSuodattimet(int[][] suodattimet) {
        numeeriset = suodattimet;
    }

    public void asetaValikkoSuodattimet(String[] merkkijonot) {
        this.merkkijonot = merkkijonot;
    }

}
